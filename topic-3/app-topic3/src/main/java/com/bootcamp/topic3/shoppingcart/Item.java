package com.bootcamp.topic3.shoppingcart;

import java.util.Objects;

/**
 * Stores the product which the visitor
 * want to buy and the solicited amount.
 */
public class Item {
  private Product product;
  private int amount;

  /**
   * Constructs a item with a given product and amount.
   * @param product
   * @param amount
   */
  public Item(Product product, int amount) {
    this.product = product;
    this.amount = amount;
  }

  /**
   * Constructs a first item with a product and
   * the amount sets to 1.
   * @param product
   */
  public Item(Product product) {
    this.product = product;
    this.amount = 1;
  }

  /**
   * Returns the product.
   * @return
   */
  public Product getProduct() {
    return product;
  }

  /**
   * Replaces the product with a given one.
   * @param product
   */
  public void setProduct(Product product) {
    this.product = product;
  }

  /**
   * Returns the amount.
   * @return
   */
  public int getAmount() {
    return amount;
  }

  /**
   * Replaces the amount with a given one.
   * @param amount
   */
  public void setAmount(int amount) {
    this.amount = amount;
  }

  /**
   * Increases the amount.
   */
  public void increaseAmount() {
    amount++;
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
    Item item = (Item) o;
    return amount == item.amount &&
        product.equals(item.product);
  }

  /**
   * Overrides hashCode method.
   * @return
   */
  @Override
  public int hashCode() {
    return Objects.hash(product, amount);
  }
}
