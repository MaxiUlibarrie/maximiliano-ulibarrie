package com.bootcamp.topic6.apptopic6.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CartItem {

  @Id
  @GeneratedValue
  private Long idcartitem;

  @Column
  private Long idcart;

  @Column
  private Long idproduct;

  @Column
  private int quantity;

  public CartItem() {}

  public CartItem(Long idcart, Long idproduct, int quantity) {
    this.idcart = idcart;
    this.idproduct = idproduct;
    this.quantity = quantity;
  }

  public Long getIdcartitem() {
    return idcartitem;
  }

  public Long getIdcart() {
    return idcart;
  }

  public Long getIdproduct() {
    return idproduct;
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
        Objects.equals(idcart, cartItem.idcart) &&
        Objects.equals(idproduct, cartItem.idproduct);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idcartitem, idcart, idproduct, quantity);
  }
}
