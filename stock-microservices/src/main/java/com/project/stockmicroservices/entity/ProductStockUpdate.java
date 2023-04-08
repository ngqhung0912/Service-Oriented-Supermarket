package com.project.stockmicroservices.entity;

import lombok.Data;

@Data
public class ProductStockUpdate {
  private Long productId;

  private int amount;

  // true for add, false for reduce
  private boolean flag;
}
