package com.bootcamp.topic3.shoppingcart;

import java.util.Objects;

/**
 * Represents the product which
 * the customer want to buy.
 */
public class Product {
  private long id;
  private String name;
  private double price;

  /**
   * Constructs a product with a given
   * product id, name and price.
   * @param id
   * @param name
   * @param price
   */
  public Product(long id, String name, double price) {
    this.id = id;
    this.name = name;
    this.price = price;
  }

  /**
   * Returns product id.
   * @return
   */
  public long getId() {
    return id;
  }

  /**
   * Replaces the product id.
   * @param id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Returns product name.
   * @return
   */
  public String getName() {
    return name;
  }

  /**Replaces the product name.
   *
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns product price.
   * @return
   */
  public double getPrice() {
    return price;
  }

  /**
   * Replaces the product price.
   * @param price
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /**
   * Overrides equals method.
   * @param o
   * @return
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return id == product.id &&
        Double.compare(product.price, price) == 0 &&
        name.equals(product.name);
  }

  /**
   * Overrides hashCode method.
   * @return
   */
  @Override
  public int hashCode() {
    return Objects.hash(id, name, price);
  }
}
