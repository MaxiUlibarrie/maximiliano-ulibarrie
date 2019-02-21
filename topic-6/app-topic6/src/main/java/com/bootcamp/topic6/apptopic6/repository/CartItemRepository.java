package com.bootcamp.topic6.apptopic6.repository;

import com.bootcamp.topic6.apptopic6.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

}
