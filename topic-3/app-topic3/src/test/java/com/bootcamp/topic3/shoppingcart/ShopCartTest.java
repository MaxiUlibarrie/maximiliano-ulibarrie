package com.bootcamp.topic3.shoppingcart;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests all ShopCart's operations.
 */
public class ShopCartTest {
  private ShopCart shopCart;
  private Product product1, product2;
  private static final double DELTA_DOUBLE = 0.0;

  @Before
  public void setUp() {
    shopCart = new ShopCart();
    product1 = new Product(1,"Phone X",600);
    product2 = new Product(2,"EReader Y",120);
    shopCart.addToCart(product1);
    shopCart.addToCart(product2);
  }

  @Test
  public void whenAddProductInCartThenItIsExistsInTheCartAndNotDuplicated() {
    shopCart.addToCart(product1);

    int duplicated = 0;
    for (Item item : shopCart.getCart()) {
      if (item.getProduct().equals(product1)) {
        duplicated++;
        if (duplicated == 2) break;
      }
    }

    assertTrue(shopCart.containsProduct(product1));
    assertEquals(1,duplicated);
    assertEquals(2,shopCart.getCart().get(0).getAmount());
  }

  @Test
  public void whenGetItemByIdThenReturnsThatItemElseReturnsNull() {
    assertEquals(product1,shopCart.getItemById(1).getProduct());
    assertEquals(product2,shopCart.getItemById(2).getProduct());
    assertNull(shopCart.getItemById(3));
  }

  @Test
  public void whenDeleteItemByIdThenTheItemIsGoneFromTheCart() {
    shopCart.deleteItemById(1);
    assertEquals(1, shopCart.getCart().size());
    assertFalse(shopCart.containsProduct(product1));
  }

  @Test
  public void whenGetAllItemsThenReturnsTheCart() {
    List<Item> allItems = new LinkedList<>();
    allItems.add(new Item(product1));
    allItems.add(new Item(product2));

    assertEquals(allItems,shopCart.getCart());
  }

  @Test
  public void whenDeleteAllItemsThenTheCartIsClear() {
    shopCart.clearCart();
    assertTrue(shopCart.getCart().isEmpty());
  }

  @Test
  public void whenGetCheckOutThenItsReturnsCorrectTotal() {
    shopCart.addToCart(product1);
    shopCart.addToCart(product2);
    shopCart.addToCart(product2);

    double checkout = 600 * 2 + 120 * 3;
    assertEquals(checkout,shopCart.checkOut(),DELTA_DOUBLE);
  }
}