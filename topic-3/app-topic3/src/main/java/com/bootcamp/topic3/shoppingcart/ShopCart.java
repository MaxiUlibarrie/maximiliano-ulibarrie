package com.bootcamp.topic3.shoppingcart;

import java.util.ArrayList;
import java.util.List;

/**
 * Allows visitors to collect items over multiple product.
 * ShopCart may view the contents of their shopping cart
 * at any time and may add or delete items as needed,
 * and do the checkout.
 */
public class ShopCart {

  /**
   * Stores the items.
   */
  private List<Item> cart;

  /**
   * Constructs a new cart as a ArrayList.
   */
  public ShopCart() {
    this.cart = new ArrayList<>();
  }

  /**
   * Constructs a new ShopCart by a given cart.
   * @param cart
   */
  public ShopCart(List<Item> cart) {
    this.cart = cart;
  }

  /**
   * Returns all stored items.
   * @return the cart
   */
  public List<Item> getCart() {
    return cart;
  }

  /**
   * Sets the current cart by a given cart.
   * @param cart
   */
  public void setCart(List<Item> cart) {
    this.cart = cart;
  }

  /**
   * Checks if a given product exists in the cart.
   * @param product
   * @return true if the given product exists in the cart
   */
  public boolean containsProduct(Product product) {
    for (Item item : cart) {
      if (item.getProduct().equals(product)) return true;
    }
    return false;
  }

  /**
   * If the given product exists in the cart
   * then increases the product's amount, else
   * creates a new item with amount 1.
   * @param product given product
   * @return true if the operation was successful
   */
  public boolean addToCart(Product product) {
    for (Item item : cart) {
      if (item.getProduct().equals(product)) {
        item.increaseAmount();
        return true;
      }
    }

    return cart.add(new Item(product));
  }

  /**
   * Gets item by product id.
   * @param id product
   * @return the item associated with the product id
   */
  public Item getItemById(int id) {
    for (Item item : cart) {
      if (item.getProduct().getId() == id) {
        return item;
      }
    }

    return null;
  }

  /**
   * Deletes item by product id.
   * @param id product
   * @return true if the operation was successful
   */
  public boolean deleteItemById(int id) {
    return cart.removeIf(item -> (item.getProduct().getId() == id));
  }

  /**
   * Clears all items from the cart.
   */
  public void clearCart() {
    cart.clear();
  }

  /**
   * Gets checkout of all items from the cart.
   * @return total price
   */
  public double checkOut() {
    double checkout = 0;
    for (Item item : cart) {
      checkout += item.getProduct().getPrice() * item.getAmount();
    }
    return checkout;
  }
}
