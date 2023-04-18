package com.project.discountedgoodreservation.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.discountedgoodreservation.entity.Product;
import com.project.discountedgoodreservation.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ReservationServiceImpl implements ReservationService {
  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  private static final String RESERVE_TOPIC = "RESERVE";

  private Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);

  @Override
  public boolean reserve(Long productId) {
    // check availability
    boolean available = checkAvailability(productId);

    if (!available) {
      // not available return error message
      return false;
    }
    // reserve the discounted good
    finalizeReservation(productId);

    // publish the information to Kafka
    Product product = new Product();
    product.setProductId(productId);
    String message = null;
    try {
      message = objectMapper.writeValueAsString(product);
    } catch (JsonProcessingException e) {
      System.out.println(e.getMessage());
    }
    kafkaTemplate.send(RESERVE_TOPIC, message);
    return true;
  }

  private boolean checkAvailability(Long productId) {
    RestTemplate restTemplate = new RestTemplate();
    try {
      ResponseEntity<String> response =  restTemplate.exchange(
          "http://stock-kubernetes-service.supermarket:91/stock-microservices/stock?productId="+productId,
          HttpMethod.GET,
          null,
          String.class);
      Product product = objectMapper.readValue(response.getBody(), Product.class);
      if (product.getCount() > 0) {
        return true;
      }
    } catch (HttpClientErrorException | JsonProcessingException e) {
      logger.error(e.getMessage());
    }
    return false;
  }

  private void finalizeReservation(Long productId) {
    // todo mark the discounted good as reserved

  }

  @KafkaListener(topics = "RESERVE")
  public void listenTest(String message) {
    System.out.println("kafka test:" + message);
  }
}
