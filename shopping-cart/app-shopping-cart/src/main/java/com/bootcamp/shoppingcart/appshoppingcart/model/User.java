package com.bootcamp.shoppingcart.appshoppingcart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long iduser;

  @Column(length = 30, unique = true)
  private String username;

  @Column(length = 30)
  private String password;

  @Column(length = 30, unique = true)
  private String email;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "iduser"),
    inverseJoinColumns = @JoinColumn(name = "idrole"),
    uniqueConstraints = {@UniqueConstraint(columnNames = {"iduser","idrole"})})
  private List<Role> roleList;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Cart> cartList;

  protected User() {}

  public User(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.cartList = new ArrayList<>();
    this.roleList = new ArrayList<>();
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

  @JsonIgnore
  public List<Role> getRoleList() {
    return roleList;
  }

  @JsonIgnore
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
        Objects.equals(password, user.password) &&
        Objects.equals(email, user.email) &&
        Objects.equals(roleList, user.roleList) &&
        Objects.equals(cartList, user.cartList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(iduser, username, password, email, roleList, cartList);
  }
}
