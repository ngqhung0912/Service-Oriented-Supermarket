package com.project.payment.entity;

import lombok.Data;

@Data
public class PaymentResponse {
  private Long userId;

  private String uuid;

  private boolean success;

  public PaymentResponse(Long userId, boolean success, String uuid) {
    this.userId = userId;
    this.success = success;
    this.uuid = uuid;
  }
}
