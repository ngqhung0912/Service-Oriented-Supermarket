package com.project.discountedgoodreservation.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.discountedgoodreservation.entity.Product;
import com.project.discountedgoodreservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {
  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  private static final String RESERVE_TOPIC = "TEST";

  @Override
  public boolean reserve(Integer productId) {
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
    product.setProductId(1L);
    String message = null;
    try {
      message = objectMapper.writeValueAsString(product);
    } catch (JsonProcessingException e) {
      System.out.println(e.getMessage());
    }
    kafkaTemplate.send(RESERVE_TOPIC, message);
    return true;
  }

  private boolean checkAvailability(int productId) {
    // todo do something
    return true;
  }

  private void finalizeReservation(int productId) {
    // todo mark the discounted good as reserved
  }

  @KafkaListener(topics = "TEST")
  public void listenTest(String message) {
    System.out.println("kafka test:" + message);
  }
}
