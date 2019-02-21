package com.bootcamp.shoppingcart.appshoppingcart.exception;

public class NotFoundProductInCart extends RuntimeException {

  public NotFoundProductInCart(Long idproduct) {
    super(String.format("Product %d doesn't exist in the cart.",idproduct));
  }
}