package com.bootcamp.shoppingcart.appshoppingcart.exception;


public class NotFoundException extends RuntimeException {

  public NotFoundException(String entity, Long id) {
    super(String.format("Could not find %s: %d.",entity,id));
  }

  public NotFoundException(String message) {
    super(message);
  }
}