package com.bootcamp.topic6.apptopic6.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

  @Id @GeneratedValue
  private Long id;

  @Column
  private String name;

  @Column
  private double price;

  public Product(String name, double price) {
    this.name = name;
    this.price = price;
  }

  public Product() {}

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public double getPrice() {
    return price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return Double.compare(product.price, price) == 0 &&
        Objects.equals(id, product.id) &&
        Objects.equals(name, product.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, price);
  }
}
