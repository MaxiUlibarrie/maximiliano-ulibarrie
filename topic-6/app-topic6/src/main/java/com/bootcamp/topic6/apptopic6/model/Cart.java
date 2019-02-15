package com.bootcamp.topic6.apptopic6.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cart {

  @Id @GeneratedValue
  private Long id;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<CartItem> cartItemList;

  @Column
  private boolean checkedOut;

  public Cart() {
    this.cartItemList = new ArrayList<>();
    this.checkedOut = false;
  }

  public Long getId() {
    return id;
  }

  public List<CartItem> getCartItemList() {
    return cartItemList;
  }

  public boolean isCheckedOut() {
    return checkedOut;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Cart cart = (Cart) o;
    return checkedOut == cart.checkedOut &&
        Objects.equals(id, cart.id) &&
        Objects.equals(cartItemList, cart.cartItemList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, cartItemList, checkedOut);
  }
}
