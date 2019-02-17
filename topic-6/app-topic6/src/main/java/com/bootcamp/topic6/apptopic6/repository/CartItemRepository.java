package com.bootcamp.topic6.apptopic6.repository;

import com.bootcamp.topic6.apptopic6.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

  @Transactional
  void deleteByIdcart(Long idcart);

}
