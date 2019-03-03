package com.bootcamp.shoppingcart.appshoppingcart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserHasntThisRoleException extends RuntimeException {

  public UserHasntThisRoleException() {
    super("This user hasn't this role.");
  }
}
