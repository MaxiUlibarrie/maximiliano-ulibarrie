package com.bootcamp.shoppingcart.appshoppingcart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserDoesntHaveThisRoleException extends RuntimeException {

  public UserDoesntHaveThisRoleException() {
    super("This user doesn't have this role.");
  }
}
