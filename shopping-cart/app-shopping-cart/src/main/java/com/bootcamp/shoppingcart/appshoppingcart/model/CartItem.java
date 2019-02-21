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
@JsonPropertyOrder(value = {"idcartitem","idproduct","quantity"})
public class CartItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idcartitem;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idproduct")
  private Product product;

  @Column
  private int quantity;

  public CartItem() {}

  public CartItem(Product product, int quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  public Long getIdcartitem() {
    return idcartitem;
  }

  @JsonIgnore
  public Product getProduct() {
    return product;
  }

  public Long getIdproduct() {
    return product.getIdproduct();
  }

  public int getQuantity() {
    return quantity;
  }

  public void incrementQuantity(int increase) {
    quantity += increase;
  }

  public void decrementQuantity(int decrease) {
    if (quantity > decrease) {
      quantity -= decrease;
    } else {
      quantity = 0;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CartItem cartItem = (CartItem) o;
    return quantity == cartItem.quantity &&
        Objects.equals(idcartitem, cartItem.idcartitem) &&
        Objects.equals(product, cartItem.product);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idcartitem, product, quantity);
  }
}
