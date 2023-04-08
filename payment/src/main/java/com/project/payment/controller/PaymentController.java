package com.project.payment.controller;

import com.project.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
  @Autowired
  private PaymentService paymentService;

  @PostMapping("/pay")
  public ResponseEntity<Boolean> pay() {
    return new ResponseEntity<>(paymentService.pay(), HttpStatus.OK);
  }
}
