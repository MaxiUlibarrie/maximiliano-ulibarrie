package com.bootcamp.shoppingcart.appshoppingcart.exception;

public class CartCheckedOutException extends RuntimeException {

  public CartCheckedOutException(Long idcart) {
    super(String.format("Cart %d is already checked out.",idcart));
  }
}
