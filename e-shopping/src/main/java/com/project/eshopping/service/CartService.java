package com.project.eshopping.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.project.eshopping.entity.Cart;
import com.project.eshopping.entity.PaymentLog;
import com.project.eshopping.entity.PaymentResponse;
import com.project.eshopping.entity.Product;
import com.project.eshopping.entity.ProductStockUpdate;
import com.project.eshopping.entity.ProductUpdateResponse;
import com.project.eshopping.repository.CartRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.kafka.common.protocol.types.Field.Str;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class CartService {
  @Autowired
  private CartRepository cartRepository;
  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  private Logger logger = LoggerFactory.getLogger(CartService.class);

  private static final String STOCK_SERVICE_ID = "localhost:8091";
  private static final String STOCK_SERVICE_ENDPOINT = "/stock-microservices/get-products?";

  private static final String UPDATE_STOCK_SERVICE_ENDPOINT = "/stock-microservices/update-stock";

  public List<Cart> getCartByUserId(Long userId) {
    return cartRepository.findByUserId(userId);
  }

  public void rollback(String uuid) {
    PaymentLog cartResponse = new PaymentLog("100", 1, "Error", uuid);
    try {
      String cartMessage = objectMapper.writeValueAsString(cartResponse);
      kafkaTemplate.send("PAYMENT_LOG", cartMessage);
    } catch (Exception ex) {
      logger.error(ex.getMessage());
    }
  }

  public Double getBillByUserId(Long userId) {
    List<Cart> carts = cartRepository.findByUserId(userId);
    List<Long> productIdList = carts.stream().map(Cart::getProductId).collect(Collectors.toList());
    String productIds = Joiner.on(',').join(productIdList);
    logger.info("--------->cart service query Id"+productIds);
    List<Product> products = getProducts(productIds);
    Map<Long, Product> productMap = products.stream().collect(Collectors.toMap(
        Product::getProductId, Function.identity()
    ));
    double result = 0.0;
    for (Cart cart: carts) {
      if (!productMap.containsKey(cart.getProductId())) {
        continue;
      }
      result += cart.getCount() * productMap.get(cart.getProductId()).getPrice();
    }
    return result;
  }

  private List<Product> getProducts(String productIds) {
    RestTemplate restTemplate = new RestTemplate();
    try {
      ResponseEntity<String> response =  restTemplate.exchange(
          "http://"+STOCK_SERVICE_ID+STOCK_SERVICE_ENDPOINT+"ids="+productIds,
          HttpMethod.GET,
          null,
          String.class);
      List<Product> products = Arrays.asList(objectMapper.readValue(response.getBody(), Product[].class));
      return products;
    } catch (HttpClientErrorException | JsonProcessingException e) {
      logger.error(e.getMessage());
    }
    return new ArrayList<>();
  }

  @KafkaListener(topics = "RESERVE")
  public void listenReservationAndAddToCart(String message) {
    try{
      Product product = objectMapper.readValue(message, Product.class);
      addProductToCart(product.getProductId());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  @KafkaListener(topics = "PAYMENT")
  @Transactional
  public void listenPaymentAndClearCarts(String message) {
    PaymentResponse response = new PaymentResponse();
    response.setSuccess(false);
    try {
       response = objectMapper.readValue(message, PaymentResponse.class);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    if(!response.isSuccess()) {
      return;
    }
    List<Cart> deletedProducts = cartRepository.deleteByUserId(response.getUserId());
    List<Long> productIdList = deletedProducts.stream().map(Cart::getProductId).toList();
    String productIds = Joiner.on(',').join(productIdList);
    try {
      PaymentLog cartResponse = new PaymentLog(productIds, 1, "Completed", response.getUuid());
      String cartMessage = objectMapper.writeValueAsString(cartResponse);
      kafkaTemplate.send("PAYMENT_LOG", cartMessage);

      for(Cart cart: deletedProducts) {
        updateStock(cart.getProductId(), cart.getCount().intValue(), false);
      }

      PaymentLog stockResponse = new PaymentLog(productIds, 2, "Completed", response.getUuid());
      String stockMessage = objectMapper.writeValueAsString(stockResponse);
      kafkaTemplate.send("PAYMENT_LOG", stockMessage);
    } catch (Exception e) {
      logger.error(e.getMessage());
      PaymentLog cartResponse = new PaymentLog(productIds, 1, "Error", response.getUuid());
      PaymentLog stockResponse = new PaymentLog(productIds, 2, "Completed", response.getUuid());
      try {
        String cartMessage = objectMapper.writeValueAsString(cartResponse);
        String stockMessage = objectMapper.writeValueAsString(stockResponse);
        kafkaTemplate.send("PAYMENT_LOG", cartMessage);
        kafkaTemplate.send("PAYMENT_LOG", stockMessage);
      } catch (Exception ex) {
        logger.error(ex.getMessage());
      }
    }
  }

  @Transactional
  public void addProductToCart(Long productId) {

    List<Cart> cartDbs = cartRepository.findByUserIdAndProductId(1L, productId);
    if (CollectionUtils.isEmpty(cartDbs)) {
      Cart cart = new Cart();
      cart.setProductId(productId);
      cart.setUserId(1L);
      cart.setCount(1.0);
      cartRepository.save(cart);
    } else {
      Cart cart = cartDbs.get(0);
      cart.setCount(cart.getCount() + 1);
      cartRepository.save(cart);
    }

  }

  @Transactional
  public boolean removeProductFromCart(Long productId) {
    Cart cart = new Cart();
    cart.setProductId(productId);
    cart.setUserId(1L);
    List<Cart> carts = cartRepository.findByUserIdAndProductId(1L, productId);
    if (CollectionUtils.isEmpty(carts)) {
      return false;
    }
    cartRepository.deleteAllById(carts.stream().map(Cart::getId).collect(Collectors.toList()));
    return true;
  }

  @KafkaListener(topics = "PAYMENT_ROLLBACK1")
  @Transactional
  public void cartRollback(String message) {
    try {
      PaymentLog log = objectMapper.readValue(message, PaymentLog.class);
      List<Long> productIds = Arrays.stream(log.getProductIds().split(",")).map(Long::parseLong)
          .toList();
      for (Long productId: productIds) {
        addProductToCart(productId);
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
  }

  @KafkaListener(topics = "PAYMENT_ROLLBACK2")
  @Transactional
  public void stockRollback(String message) {
    try {
      PaymentLog log = objectMapper.readValue(message, PaymentLog.class);
      List<Long> productIds = Arrays.stream(log.getProductIds().split(",")).map(Long::parseLong)
          .toList();
      for (Long productId: productIds) {
        updateStock(productId, 1, true);
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
  }

  public void updateStock(Long productId, int amount, boolean flag) {
    RestTemplate restTemplate = new RestTemplate();
    ProductStockUpdate stockUpdate = new ProductStockUpdate();
    stockUpdate.setProductId(productId);
    stockUpdate.setAmount(amount);
    stockUpdate.setFlag(flag);
    String url = "http://"+STOCK_SERVICE_ID+UPDATE_STOCK_SERVICE_ENDPOINT;
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    try{
      HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(stockUpdate), headers);
      restTemplate.postForObject(url, entity, String.class);
    } catch (Exception e){
      logger.error(e.getMessage());
    }
  }
}
