package com.project.payment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.payment.entity.PaymentLog;
import com.project.payment.entity.PaymentResponse;
import com.project.payment.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
public class PaymentService {
  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private PaymentRepository paymentRepository;

  private Logger logger = LoggerFactory.getLogger(PaymentService.class);

  @Transactional
  public boolean pay() {
    PaymentLog log = new PaymentLog();
    log.setUuid(UUID.randomUUID().toString());
    log.setEventId(0);
    log.setStatus("created");
    paymentRepository.save(log);
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
    PaymentResponse response = new PaymentResponse(1L, true, log.getUuid());
    try {
      message = objectMapper.writeValueAsString(response);
    } catch (JsonProcessingException e) {
      System.out.println(e.getMessage());
    }
    kafkaTemplate.send("PAYMENT", message);
    return true;
  }

  @KafkaListener(topics = "PAYMENT_LOG")
  @Transactional
  public void monitor(String message) {
    try {
      PaymentLog paymentLog = objectMapper.readValue(message, PaymentLog.class);
      List<PaymentLog> paymentLogs = paymentRepository.getAllByUuid(paymentLog.getUuid());
      boolean rollback = false;
      PaymentLog initialLog = null;
      List<PaymentLog> logEvents = new ArrayList<>();
      for (PaymentLog log: paymentLogs) {
        if (log.getEventId() == 0) {
          initialLog = log;
          if ("Error".equals(log.getStatus())) {
            rollback = true;
          }
        } else {
          logEvents.add(log);
        }
      }
      if (rollback) {
        sendRollBackMessage(paymentLog);
        return;
      }
      paymentRepository.save(paymentLog);
      if ("Error".equals(paymentLog.getStatus())) {
        initialLog.setStatus("Error");
        paymentRepository.save(initialLog);
        batchRollBack(logEvents);
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
  }

  private void sendRollBackMessage(PaymentLog paymentLog) {
    try {
      paymentLog.setStatus("Error");
      String rollbackMessage = objectMapper.writeValueAsString(paymentLog);
      kafkaTemplate.send("PAYMENT_ROLLBACK"+paymentLog.getEventId(), rollbackMessage);
      paymentRepository.save(paymentLog);
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
    }
  }

  private void batchRollBack(List<PaymentLog> paymentLogs) {
    try{
      for (PaymentLog paymentLog: paymentLogs) {
        paymentLog.setStatus("Error");
        String rollbackMessage = objectMapper.writeValueAsString(paymentLog);
        kafkaTemplate.send("PAYMENT_ROLLBACK"+paymentLog.getEventId(), rollbackMessage);
        paymentRepository.save(paymentLog);
      }
    } catch (Exception e) {

    }
  }
}
