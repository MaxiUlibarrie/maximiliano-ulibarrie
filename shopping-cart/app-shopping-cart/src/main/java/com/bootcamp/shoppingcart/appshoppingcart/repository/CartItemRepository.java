package com.bootcamp.shoppingcart.appshoppingcart.repository;

import com.bootcamp.shoppingcart.appshoppingcart.model.Cart;
import com.bootcamp.shoppingcart.appshoppingcart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {

  @Transactional
  void deleteByCart(Cart cart);
}
