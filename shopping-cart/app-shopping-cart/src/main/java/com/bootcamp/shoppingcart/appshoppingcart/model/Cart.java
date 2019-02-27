package com.bootcamp.shoppingcart.appshoppingcart.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@JsonPropertyOrder(value = {"idcart","user","checkedOut"})
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idcart;

  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
  private List<CartItem> cartItemList;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "iduser")
  private User user;

  @Column
  private boolean checkedOut;

  protected Cart() {}

  public Cart(User user) {
    this.user = user;
    this.cartItemList = new ArrayList<>();
    this.checkedOut = false;
  }

  public Long getIdcart() {
    return idcart;
  }

  @JsonIgnore
  public List<CartItem> getCartItemList() {
    return cartItemList;
  }

  public boolean isCheckedOut() {
    return checkedOut;
  }

  @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="iduser")
  @JsonIdentityReference(alwaysAsId = true)
  public User getUser() {
    return user;
  }

  public void setCheckedOut(boolean checkedOut) {
    this.checkedOut = checkedOut;
  }

  public boolean existsProduct(Long idproduct) {
    return cartItemList.stream()
        .anyMatch(cartItem -> cartItem.getProduct().getIdproduct().equals(idproduct));
  }

  public CartItem getOneCartItem(Long idproduct) {
    controlNotFoundProductInCart(idproduct);

    return cartItemList.stream()
        .filter(cartItem -> cartItem.getProduct().getIdproduct().equals(idproduct))
        .findAny()
        .get();
  }

  public boolean deleteProduct(Long idproduct) {
    controlNotFoundProductInCart(idproduct);

    return cartItemList
        .removeIf(cartItem -> cartItem.getProduct().getIdproduct().equals(idproduct));
  }

  private void controlNotFoundProductInCart(Long idproduct) {
    if (!existsProduct(idproduct))
      throw new RuntimeException(String.format("Product %d doesn't exist in the cart.",idproduct));
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
        Objects.equals(cartItemList, cart.cartItemList) &&
        Objects.equals(user, cart.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idcart, cartItemList, user, checkedOut);
  }
}
