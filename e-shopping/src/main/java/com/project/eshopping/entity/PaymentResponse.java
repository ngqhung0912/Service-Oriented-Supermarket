package com.project.eshopping.entity;

import lombok.Data;

@Data
public class PaymentResponse {
  private Long userId;

  private boolean success;

  private String uuid;

  public PaymentResponse(Long userId, boolean success, String uuid) {
    this.userId = userId;
    this.success = success;
    this.uuid = uuid;
  }

  public PaymentResponse() {

  }
}
