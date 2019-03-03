package com.bootcamp.shoppingcart.appshoppingcart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductDoesntExistInCartException extends RuntimeException {

  public ProductDoesntExistInCartException(Long idproduct) {
    super(String.format("Product %d doesn't exist in the cart.",idproduct));
  }
}
