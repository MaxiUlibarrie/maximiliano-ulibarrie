package com.bootcamp.topic6.apptopic6.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idcategory;

  @Column(length = 30)
  private String name;

  @Column(length = 30)
  private String description;

  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
  private List<Product> productList;

  public Category() {}

  public Category(String name) {
    this.name = name;
  }


}
