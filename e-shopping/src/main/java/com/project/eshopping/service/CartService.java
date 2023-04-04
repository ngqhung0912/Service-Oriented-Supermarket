package com.project.eshopping.service;

import com.project.eshopping.entity.Cart;
import com.project.eshopping.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
  @Autowired
  private CartRepository cartRepository;

  public void addProductToCart(Long productId) {
    //todo add product
    Cart cart = new Cart();
    cart.setProductId(productId);
    cart.setUserId(1L);
    cartRepository.save(cart);
  }

  public void removeProductFromCart(Long productId) {
    //todo remove product
    cartRepository.deleteById(productId);
  }
}
