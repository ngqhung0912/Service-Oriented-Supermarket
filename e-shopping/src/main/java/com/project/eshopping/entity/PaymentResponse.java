package com.project.eshopping.entity;

import lombok.Data;

@Data
public class PaymentResponse {
  private Long userId;

  private boolean success;
}
