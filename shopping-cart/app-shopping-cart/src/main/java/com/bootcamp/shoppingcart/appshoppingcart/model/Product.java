package com.bootcamp.shoppingcart.appshoppingcart.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonPropertyOrder(value = {"idproduct","name","category","manufacturer","price"})
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idproduct;

  @Column(length = 50)
  private String name;

  @Column
  private double price;

  @Column(length = 40)
  private String manufacturer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idcategory")
  private Category category;

  public Product(String name, double price,
      Category category, String manufacturer) {
    this.name = name;
    this.price = price;
    this.category = category;
    this.manufacturer = manufacturer;
  }

  protected Product() {}

  public Long getIdproduct() {
    return idproduct;
  }

  public String getName() {
    return name;
  }

  public double getPrice() {
    return price;
  }

  @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="idcategory")
  @JsonIdentityReference(alwaysAsId=true)
  public Category getCategory() {
    return category;
  }

  public String getManufacturer() {
    return manufacturer;
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
        Objects.equals(name, product.name) &&
        Objects.equals(manufacturer, product.manufacturer) &&
        Objects.equals(category, product.category);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idproduct, name, price, manufacturer, category);
  }
}
