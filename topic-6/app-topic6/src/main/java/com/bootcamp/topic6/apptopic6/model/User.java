package com.bootcamp.topic6.apptopic6.model;

import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

  @Id @GeneratedValue
  private Long iduser;

  @Column
  private String username;

  @Column
  private String password;

  @Column
  private String email;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  private List<Cart> cartList;

  protected User() {}

  public User(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
  }

  public Long getIduser() {
    return iduser;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }

  public List<Cart> getCartList() {
    return cartList;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Cart lastCart() {
    return cartList.get(cartList.size() - 1);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(iduser, user.iduser) &&
        Objects.equals(username, user.username) &&
        Objects.equals(password, user.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(iduser, username, password);
  }
}
