package com.bootcamp.shoppingcart.appshoppingcart.repository;

import com.bootcamp.shoppingcart.appshoppingcart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

}
