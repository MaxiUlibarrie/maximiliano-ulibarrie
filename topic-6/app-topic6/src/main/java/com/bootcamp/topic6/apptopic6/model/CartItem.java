package com.bootcamp.topic6.apptopic6.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CartItem {

  @Id
  @GeneratedValue
  @JsonIgnore
  private Long id;

  @ManyToOne
  @JsonIgnore
  private Cart cart;

  @Column
  private Long idProduct;

  @Column
  private int quantity;

  public CartItem(Cart cart, Long idProduct, int quantity) {
    this.cart = cart;
    this.idProduct = idProduct;
    this.quantity = quantity;
  }

  public CartItem() {}

  public Long getId() {
    return id;
  }

  public Cart getCart() {
    return cart;
  }

  public Long getIdProduct() {
    return idProduct;
  }

  public int getQuantity() {
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
    CartItem cartItem = (CartItem) o;
    return quantity == cartItem.quantity &&
        Objects.equals(id, cartItem.id) &&
        Objects.equals(cart, cartItem.cart) &&
        Objects.equals(idProduct, cartItem.idProduct);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, cart, idProduct, quantity);
  }
}
