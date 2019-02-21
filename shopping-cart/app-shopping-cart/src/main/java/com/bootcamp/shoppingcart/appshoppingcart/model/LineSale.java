package com.bootcamp.shoppingcart.appshoppingcart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
@JsonPropertyOrder(value = {"idLineSale","idsale","idproduct","quantity","subTotalPrice"})
public class LineSale {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idLineSale;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idsale")
  private Sale sale;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idproduct")
  private Product product;

  @Column
  private Integer quantity;

  @Column
  private double subTotalPrice;

  public LineSale(Sale sale, Product product, Integer quantity) {
    this.sale = sale;
    this.product = product;
    this.quantity = quantity;
    this.subTotalPrice = product.getPrice() * quantity;
  }

  protected LineSale() {}

  public Long getIdLineSale() {
    return idLineSale;
  }

  @JsonIgnore
  public Sale getSale() {
    return sale;
  }

  public long getIdsale() {
    return sale.getIdSale();
  }

  @JsonIgnore
  public Product getProduct() {
    return product;
  }

  public Long getIdproduct() {
    return product.getIdproduct();
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
    return Double.compare(lineSale.subTotalPrice, subTotalPrice) == 0 &&
        Objects.equals(idLineSale, lineSale.idLineSale) &&
        Objects.equals(sale, lineSale.sale) &&
        Objects.equals(product, lineSale.product) &&
        Objects.equals(quantity, lineSale.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idLineSale, sale, product, quantity, subTotalPrice);
  }
}
