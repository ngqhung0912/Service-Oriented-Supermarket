package com.project.payment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.payment.entity.PaymentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {
  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  private Logger logger = LoggerFactory.getLogger(PaymentService.class);

  public boolean pay() {
    RestTemplate restTemplate = new RestTemplate();
    Double amount = 0.0;
    String userId = "1";
    try {
      ResponseEntity<String> response =  restTemplate.exchange(
          "http://localhost:8082/carts/bill/"+userId,
          HttpMethod.GET,
          null,
          String.class);
      amount =Double.parseDouble(response.getBody());
    } catch (HttpClientErrorException e) {
      logger.error(e.getMessage());
    }
    logger.info("Payment proceed with amount: " + amount);

    String message = null;
    PaymentResponse response = new PaymentResponse(1L, true);
    try {
      message = objectMapper.writeValueAsString(response);
    } catch (JsonProcessingException e) {
      System.out.println(e.getMessage());
    }
    kafkaTemplate.send("PAYMENT", message);
    return true;
  }
}
