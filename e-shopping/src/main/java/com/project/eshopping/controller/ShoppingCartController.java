package com.project.eshopping.controller;

import com.project.eshopping.entity.Cart;
import com.project.eshopping.repository.CartRepository;
import com.project.eshopping.service.CartService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ShoppingCartController {

  @Autowired
  private CartService cartService;

  @Autowired
  private CartRepository cartRepository;

  @PostMapping("/add/{productId}")
  public ResponseEntity addProductToCart(@PathVariable Long productId) {
    cartService.addProductToCart(productId);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/remove/{productId}")
  public ResponseEntity deleteProductFromCart(@PathVariable Long productId) {
    boolean success = cartService.removeProductFromCart(productId);
    if (success) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @GetMapping("/overview")
  public String overview(Model model) {
    model.addAttribute("carts", cartRepository.findAll());
    return "overview";
  }

  @GetMapping("/carts/{userId}")
  public ResponseEntity<List<Cart>> getCarts(@PathVariable Long userId) {
    return new ResponseEntity<>(cartService.getCartByUserId(userId), HttpStatus.OK);
  }

  @GetMapping("/carts/bill/{userId}")
  public ResponseEntity<Double> getBill(@PathVariable Long userId) {
    return new ResponseEntity<>(cartService.getBillByUserId(userId), HttpStatus.OK);
  }
}
