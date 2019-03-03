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
@JsonPropertyOrder(value = {"idcartitem","cart","product","quantity"})
public class CartItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idcartitem;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idproduct")
  private Product product;

  @Column
  private int quantity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idcart")
  private Cart cart;

  protected CartItem() {}

  public CartItem(Cart cart, Product product, int quantity) {
    this.cart = cart;
    this.product = product;
    this.quantity = quantity;
  }

  public Long getIdcartitem() {
    return idcartitem;
  }

  @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="idproduct")
  @JsonIdentityReference(alwaysAsId = true)
  public Product getProduct() {
    return product;
  }

  public int getQuantity() {
    return quantity;
  }

  @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="idcart")
  @JsonIdentityReference(alwaysAsId = true)
  public Cart getCart() {
    return cart;
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
