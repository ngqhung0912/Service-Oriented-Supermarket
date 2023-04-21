package com.project.payment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "payment_log")
public class PaymentLog {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String productIds;

  /**
   * 0 -> payment event
   * 1 -> cart event
   * 2 -> stock event
   */
  private Integer eventId;
  private String status;

  private String uuid;
}
