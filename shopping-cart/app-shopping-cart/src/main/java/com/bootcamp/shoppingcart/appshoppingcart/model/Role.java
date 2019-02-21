package com.bootcamp.shoppingcart.appshoppingcart.model;

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
import javax.persistence.ManyToMany;

@Entity
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idrole;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "iduser")
  private List<User> userList;

  @Column(length = 30)
  private String name;

  public Role() {}

  public Role(String name) {
    this.name = name;
  }

  public Long getIdrole() {
    return idrole;
  }

  public List<User> getUserList() {
    return userList;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Role role = (Role) o;
    return Objects.equals(idrole, role.idrole) &&
        Objects.equals(userList, role.userList) &&
        Objects.equals(name, role.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idrole, userList, name);
  }
}
