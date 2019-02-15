package com.bootcamp.topic6.apptopic6.dao;

import com.bootcamp.topic6.apptopic6.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemDAO extends JpaRepository<CartItem,Long> {

}
