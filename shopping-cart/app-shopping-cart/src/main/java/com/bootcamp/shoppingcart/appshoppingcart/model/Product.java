package com.bootcamp.shoppingcart.appshoppingcart.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idproduct;

  @Column(length = 30)
  private String name;

  @Column
  private double price;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idcategory")
  private Category category;

  public Product(String name, double price) {
    this.name = name;
    this.price = price;
  }

  public Product() {}

  public Long getIdproduct() {
    return idproduct;
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
        Objects.equals(idproduct, product.idproduct) &&
        Objects.equals(name, product.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idproduct, name, price);
  }
}
