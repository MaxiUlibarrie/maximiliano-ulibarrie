package com.bootcamp.topic6.apptopic6.service;

import com.bootcamp.topic6.apptopic6.model.Cart;
import java.util.List;

public interface CartService {

  List<Cart> getAllCarts();
  void deleteCartById(Long idcart);
  Cart getCartById(Long idcart);

}
