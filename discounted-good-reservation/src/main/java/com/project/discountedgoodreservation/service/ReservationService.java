package com.project.discountedgoodreservation.service;

/**
 * Reservation service interface
 */
public interface ReservationService {

  /**
   * Reserve for a certain product
   *
   * @param productId productId
   */
  boolean reserve(Long productId);
}
