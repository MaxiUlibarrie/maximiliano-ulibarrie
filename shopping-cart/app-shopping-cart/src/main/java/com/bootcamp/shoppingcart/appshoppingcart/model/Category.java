package com.bootcamp.shoppingcart.appshoppingcart.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idcategory;

  @Column(length = 30)
  private String name;

  @Column(length = 30)
  private String description;

  protected Category() {}

  public Category(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public Long getIdcategory() {
    return idcategory;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Category category = (Category) o;
    return Objects.equals(idcategory, category.idcategory) &&
        Objects.equals(name, category.name) &&
        Objects.equals(description, category.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idcategory, name, description);
  }
}
