package com.bootcamp.topic6.apptopic6.repository;

import com.bootcamp.topic6.apptopic6.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {

}
