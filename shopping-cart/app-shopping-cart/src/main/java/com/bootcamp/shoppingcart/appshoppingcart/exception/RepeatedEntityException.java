package com.bootcamp.shoppingcart.appshoppingcart.exception;

public class RepeatedEntityException extends RuntimeException {

  public RepeatedEntityException(String entity) {
    super(String.format("%s already exists.",entity));
  }
}
