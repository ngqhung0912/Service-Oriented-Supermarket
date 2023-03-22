package com.project.discountedgoodreservation.service.impl;

import com.project.discountedgoodreservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {
  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  private static final String RESERVE_TOPIC = "";

  @Override
  public boolean reserve(Integer productId) {
    // check availability
    boolean available = checkAvailability(productId);

    if (!available) {
      // not available return error message
      return false;
    }
    // reserve the discounted good
    finalizeReservation(productId);

    // publish the information to Kafka
    String message = "";
    kafkaTemplate.send(RESERVE_TOPIC, message);
    return true;
  }

  private boolean checkAvailability(int productId) {
    // todo do something
    return true;
  }

  private void finalizeReservation(int productId) {
    // todo mark the discounted good as reserved
  }
}
