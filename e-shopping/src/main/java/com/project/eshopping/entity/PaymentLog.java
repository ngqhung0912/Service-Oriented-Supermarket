package com.project.eshopping.entity;

import lombok.Data;

@Data
public class PaymentLog {
  private String productIds;

  /**
   * 0 -> payment event
   * 1 -> cart event
   * 2 -> stock event
   */
  private Integer eventId;
  private String status;

  private String uuid;

  public PaymentLog(String productIds, Integer eventId, String status, String uuid) {
    this.productIds = productIds;
    this.eventId = eventId;
    this.status = status;
    this.uuid = uuid;
  }
}
