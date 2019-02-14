package com.bootcamp.topic6.apptopic6.service;

import com.bootcamp.topic6.apptopic6.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(value = { "INITIAL_QUANTITY" })
public class Item {

  private Product product;
  private int quantity;
  private final int INITIAL_QUANTITY = 1;

  public Item(Product product) {
    this.product = product;
    this.quantity = INITIAL_QUANTITY;
  }

  public void incrementQuantity() {
    quantity++;
  }
}
