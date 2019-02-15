package com.bootcamp.topic6.apptopic6.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class LineSale {

  @Id @GeneratedValue
  private Long id;

  @ManyToOne
  private Product product;

  @Column
  private Integer quantity;

  public LineSale(Product product, Integer quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  public LineSale() {}

  public Long getId() {
    return id;
  }

  public Product getProduct() {
    return product;
  }

  public Integer getQuantity() {
    return quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LineSale lineSale = (LineSale) o;
    return Objects.equals(id, lineSale.id) &&
        Objects.equals(product, lineSale.product) &&
        Objects.equals(quantity, lineSale.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, product, quantity);
  }
}
