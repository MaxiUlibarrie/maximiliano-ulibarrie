package com.bootcamp.topic6.apptopic6.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
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
}
