package com.project.payment.entity;

import lombok.Data;

@Data
public class PaymentResponse {
  private Long userId;

  private boolean success;

  public PaymentResponse(Long userId, boolean success) {
    this.userId = userId;
    this.success = success;
  }
}
