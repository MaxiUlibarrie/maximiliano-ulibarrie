package com.bootcamp.topic6.apptopic6.service;

import com.bootcamp.topic6.apptopic6.model.Cart;
import com.bootcamp.topic6.apptopic6.repository.CartRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cartService")
public class CartServiceImp implements CartService {

  private final CartRepository cartRepo;

  @Autowired
  public CartServiceImp(CartRepository cartRepo) {
    this.cartRepo = cartRepo;
  }

  @Override
  public List<Cart> getAllCarts() {
    return cartRepo.findAll();
  }

  @Override
  public void deleteCartById(Long idcart) {
    cartRepo.deleteById(idcart);
  }

  @Override
  public Cart getCartById(Long idcart) {
    return cartRepo.findById(idcart).get();
  }
}
