package com.project.eshopping.entity;

import lombok.Data;

@Data
public class ProductStockUpdate {
  private Long productId;

  private Integer amount;

  // true for add, false for reduce
  private boolean flag;
}