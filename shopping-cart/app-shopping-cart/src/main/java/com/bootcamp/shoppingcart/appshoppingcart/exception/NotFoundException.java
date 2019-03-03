package com.bootcamp.shoppingcart.appshoppingcart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

  public NotFoundException(String entity, Long id) {
    super(String.format("Could not find %s: %d.",entity,id));
  }

  public NotFoundException(String entity, String property) {
    super(String.format("Could not find %s: %s.",entity,property));
  }

  public NotFoundException(String message) {
    super(message);
  }
}