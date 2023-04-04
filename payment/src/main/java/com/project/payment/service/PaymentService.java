package com.project.payment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
  Logger logger = LoggerFactory.getLogger(PaymentService.class);
  public boolean pay(Double amount) {
    logger.info("Payment proceed with amount: " + amount);
    return true;
  }
}
