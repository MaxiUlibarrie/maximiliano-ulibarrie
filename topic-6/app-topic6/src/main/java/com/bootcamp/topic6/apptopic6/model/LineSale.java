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
  private Long idLineSale;

  @Column
  private Long idsale;

  @ManyToOne
  private Product product;

  @Column
  private Integer quantity;

  @Column
  private double subTotalPrice;

  public LineSale(Long idsale, Product product, Integer quantity) {
    this.idsale = idsale;
    this.product = product;
    this.quantity = quantity;
    this.subTotalPrice = product.getPrice() * quantity;
  }

  protected LineSale() {}

  public Long getIdLineSale() {
    return idLineSale;
  }

  public Long getIdsale() {
    return idsale;
  }

  public Product getProduct() {
    return product;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public double getSubTotalPrice() {
    return subTotalPrice;
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
    return Objects.equals(idLineSale, lineSale.idLineSale) &&
        Objects.equals(product, lineSale.product) &&
        Objects.equals(quantity, lineSale.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idLineSale, product, quantity);
  }
}
