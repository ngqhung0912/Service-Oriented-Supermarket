package com.project.eshopping.entity;

import lombok.Data;

@Data
public class ProductUpdateResponse {
  private boolean success;

  private String errorMessage;

  public ProductUpdateResponse(boolean success, String errorMessage) {
    this.success = success;
    this.errorMessage = errorMessage;
  }
}
