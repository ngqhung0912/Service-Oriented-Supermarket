package com.project.stockmicroservices.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.stockmicroservices.entity.Product;
import com.project.stockmicroservices.entity.ProductStockUpdate;
import com.project.stockmicroservices.entity.ProductUpdateResponse;
import com.project.stockmicroservices.repository.StockRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StockService {
  private Logger logger = LoggerFactory.getLogger(StockService.class);
  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private StockRepository stockRepository;

  @KafkaListener(topics = "TEST")
  public void listenTest(String message) {
    try{
      Product product = objectMapper.readValue(message, Product.class);
      System.out.println("message:" + message);
      System.out.println("object" + product);
      System.out.println("id:" + product.getProductId());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public ProductUpdateResponse updateProductInfo(ProductStockUpdate stockUpdate) {
    Optional<Product> productDbOptional = stockRepository.findById(stockUpdate.getProductId());
    if (productDbOptional.isPresent()) {
      Product productDb = productDbOptional.get();
      int currentStock  = productDb.getCount();
      int updatedStock;
      // update count
      if (stockUpdate.isFlag()) {
        updatedStock = currentStock + stockUpdate.getAmount();
      } else {
        updatedStock = currentStock - stockUpdate.getAmount();
      }
      if (updatedStock < 0) {
        return new ProductUpdateResponse(false, "Not enough Stock");
      }
      productDb.setCount(updatedStock);
      stockRepository.save(productDb);
      return new ProductUpdateResponse(true, "");
    } else {
      return new ProductUpdateResponse(false, "Unknown error");
    }
  }
}
