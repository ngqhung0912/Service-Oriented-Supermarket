package com.project.discountedgoodreservation.controller;

import com.project.discountedgoodreservation.service.ReservationService;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

  @Autowired
  private ReservationService reservationService;

  @PostMapping("/{productId}/reserve")
  public ResponseEntity reserve(@PathVariable Long productId) {
    if (reservationService.reserve(productId)) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }
}
