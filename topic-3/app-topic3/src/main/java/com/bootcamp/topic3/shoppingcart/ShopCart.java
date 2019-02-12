package com.bootcamp.topic3.shoppingcart;

import java.util.ArrayList;
import java.util.List;

/**
 * Allows visitors to collect items over multiple product.
 * ShopCart may view the contents of their shopping cart
 * at any time and may add or delete items as needed,
 * and do the check out.
 */
public class ShopCart {

  /**
   * Stores the items.
   */
  private List<Item> cart;
  /**
   * Stores the state of the cart, if the visitor
   * paid the shop.
   */
  private boolean checkedOut;

  /**
   * Constructs a new cart as a ArrayList.
   */
  public ShopCart() {
    this.cart = new ArrayList<>();
    this.checkedOut = false;
  }

  /**
   * Constructs a new ShopCart by a given cart.
   * @param cart
   */
  public ShopCart(List<Item> cart) {
    this.cart = cart;
    this.checkedOut = false;
  }

  /**
   * Returns all stored items.
   * @return the cart
   */
  public List<Item> getCart() {
    return cart;
  }

  /**
   * Checks if the cart is checked out.
   * @return
   */
  public boolean isCheckedOut() {
    return checkedOut;
  }

  /**
   * Checks if a given product exists in the cart.
   * @param product
   * @return true if the given product exists in the cart
   */
  public boolean containsProduct(Product product) {
    Item item = getItemById(product.getId());

    return (item != null);
  }

  /**
   * If the given product exists in the cart
   * then increases the product's amount, else
   * creates a new item with amount 1.
   * @param product given product
   * @return true if the operation was successful
   */
  public boolean addToCart(Product product) {
    if (containsProduct(product)) {
      getItemById(product.getId()).increaseAmount();
      return true;
    }

    return cart.add(new Item(product));
  }

  /**
   * Gets item by product id.
   * @param id product
   * @return the item associated with the product id
   */
  public Item getItemById(long id) {
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
  public boolean deleteItemById(long id) {
    return cart.removeIf(item -> (item.getProduct().getId() == id));
  }

  /**
   * Clears all items from the cart.
   */
  public void clearCart() {
    cart.clear();
  }

  /**
   * Gets checkedOut of all items from the cart.
   * @return total price
   */
  public double doCheckOut() {
    double totalPrice = 0;
    for (Item item : cart) {
      totalPrice += item.getProduct().getPrice() * item.getAmount();
    }
    checkedOut = true;

    return totalPrice;
  }
}
