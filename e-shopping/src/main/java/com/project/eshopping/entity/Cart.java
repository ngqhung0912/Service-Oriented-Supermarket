package com.project.eshopping.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "shopping_cart")
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Long userId;

  private Long productId;

  private Double count;
}
