package com.project.eshopping.controller;

import com.project.eshopping.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCartController {

  @Autowired
  private CartService cartService;

  @PostMapping("/add/{productId}")
  public ResponseEntity addProductToCart(@PathVariable Long productId) {
    cartService.addProductToCart(productId);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/remove/{productId}")
  public ResponseEntity deleteProductFromCart(@PathVariable Long productId) {
    cartService.removeProductFromCart(productId);
    return ResponseEntity.ok().build();
  }
}
