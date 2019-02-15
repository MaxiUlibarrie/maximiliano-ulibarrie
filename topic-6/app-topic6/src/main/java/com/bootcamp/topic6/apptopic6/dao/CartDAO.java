package com.bootcamp.topic6.apptopic6.dao;

import com.bootcamp.topic6.apptopic6.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDAO extends JpaRepository<Cart,Long> {

}
