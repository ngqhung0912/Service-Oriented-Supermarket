package com.project.discountedgoodreservation.entity;

import lombok.Data;

@Data
public class Product {

  private Long productId;

  private String name;

  private String description;

  private double price;

  private int count;
}
