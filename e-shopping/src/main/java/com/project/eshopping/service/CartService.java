package com.project.eshopping.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.project.eshopping.entity.Cart;
import com.project.eshopping.entity.Product;
import com.project.eshopping.repository.CartRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

  private Logger logger = LoggerFactory.getLogger(CartService.class);

  private static final String STOCK_SERVICE_ID = "localhost:8091";
  private static final String STOCK_SERVICE_ENDPOINT = "/stock-microservices/get-products?";

  public List<Cart> getCartByUserId(Long userId) {
    return cartRepository.findByUserId(userId);
  }

  public Double getBillByUserId(Long userId) {
    List<Cart> carts = cartRepository.findByUserId(userId);
    List<Long> productIdList = carts.stream().map(Cart::getProductId).collect(Collectors.toList());
    String productIds = Joiner.on(',').join(productIdList);
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

  @Transactional
  public void addProductToCart(Long productId) {
    //todo add product
    Cart cart = new Cart();
    cart.setProductId(productId);
    cart.setUserId(1L);
    cartRepository.save(cart);
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
}
