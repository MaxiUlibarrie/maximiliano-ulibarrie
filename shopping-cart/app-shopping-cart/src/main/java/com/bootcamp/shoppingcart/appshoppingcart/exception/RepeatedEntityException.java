package com.bootcamp.shoppingcart.appshoppingcart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RepeatedEntityException extends RuntimeException {

  public RepeatedEntityException(String entity) {
    super(String.format("%s already exists.",entity));
  }
}
