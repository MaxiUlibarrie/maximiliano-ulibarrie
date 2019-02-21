package com.bootcamp.topic6.apptopic6.exception;

public class CartCheckedOutException extends RuntimeException {

  public CartCheckedOutException(Long idcart) {
    super(String.format("Cart %d is already checked out.",idcart));
  }
}
