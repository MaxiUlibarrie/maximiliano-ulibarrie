package com.bootcamp.topic6.apptopic6.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Cart {

  @Id @GeneratedValue
  private Long idcart;

  @OneToMany(cascade = CascadeType.ALL)
  private List<CartItem> cartItemList;

  @ManyToOne
  @JoinColumn(name = "iduser")
  private User user;

  @Column
  private boolean checkedOut;

  public Cart(User user) {
    this.user = user;
    this.cartItemList = new ArrayList<>();
    this.checkedOut = false;
  }

  public Cart() {}

  public Long getIdcart() {
    return idcart;
  }

  public List<CartItem> getCartItemList() {
    return cartItemList;
  }

  public boolean isCheckedOut() {
    return checkedOut;
  }

  public void setCheckedOut(boolean checkedOut) {
    this.checkedOut = checkedOut;
  }

  public boolean existsProduct(Long idproduct) {
    return cartItemList.stream()
        .anyMatch(cartItem -> cartItem.getIdproduct().equals(idproduct));
  }

  public CartItem getCartItemByIdProduct(Long idproduct) {
    return cartItemList.stream()
        .filter(cartItem -> cartItem.getIdproduct().equals(idproduct))
        .findAny()
        .orElse(null);
  }

  public boolean deleteProduct(Long idproduct) {
    return cartItemList
        .removeIf(cartItem -> cartItem.getIdproduct().equals(idproduct));
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
        Objects.equals(idcart, cart.idcart) &&
        Objects.equals(cartItemList, cart.cartItemList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idcart, cartItemList, checkedOut);
  }
}
