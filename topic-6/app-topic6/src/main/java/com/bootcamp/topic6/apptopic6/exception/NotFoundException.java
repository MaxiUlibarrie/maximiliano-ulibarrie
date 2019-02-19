package com.bootcamp.topic6.apptopic6.exception;

public class NotFoundException extends RuntimeException {

  public NotFoundException(String entity, Long id) {
    super(String.format("Could not find %s: %d.",entity,id));
  }
}