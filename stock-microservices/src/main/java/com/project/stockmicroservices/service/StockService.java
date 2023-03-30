package com.project.stockmicroservices.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.stockmicroservices.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StockService {

  @Autowired
  private ObjectMapper objectMapper;

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
}
