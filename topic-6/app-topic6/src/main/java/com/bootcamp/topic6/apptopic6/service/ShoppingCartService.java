package com.bootcamp.topic6.apptopic6.service;

import com.bootcamp.topic6.apptopic6.model.CartItem;
import com.bootcamp.topic6.apptopic6.model.Product;
import java.util.List;

public interface ShoppingCartService {

  List<CartItem> getAllCartItems();
  boolean addToCart(Long idProduct, Integer quantity);
  Product getProductById(Long idProduct);
  boolean removeProductById(Long id, Integer quantity);
  boolean clearCart();
  double doCheckOut();

}
