package com.bootcamp.topic6.apptopic6.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;

@Data
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
}
