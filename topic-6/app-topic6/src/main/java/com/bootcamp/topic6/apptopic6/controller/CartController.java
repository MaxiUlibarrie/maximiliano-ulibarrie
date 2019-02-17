package com.bootcamp.topic6.apptopic6.controller;

import com.bootcamp.topic6.apptopic6.model.Cart;
import com.bootcamp.topic6.apptopic6.service.CartService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

  @Autowired
  private CartService cartService;

  @GetMapping("/carts")
  public List<Cart> getAllCarts() {
    return cartService.getAllCarts();
  }

  @DeleteMapping("/deletecart/{idcart}")
  public void deleteCartById(@PathVariable Long idcart) {
    cartService.deleteCartById(idcart);
  }

  @GetMapping("/carts/{idcart}")
  public Cart getCartById(@PathVariable Long idcart) {
    return cartService.getCartById(idcart);
  }

}
