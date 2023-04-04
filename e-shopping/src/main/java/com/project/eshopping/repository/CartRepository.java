package com.project.eshopping.repository;

import com.project.eshopping.entity.Cart;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
  List<Cart> findByUserIdAndProductId(Long userId, Long productId);

  List<Cart> findByUserId(Long userId);
}
