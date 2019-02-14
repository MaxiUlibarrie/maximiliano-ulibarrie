package com.bootcamp.topic6.apptopic6.service;

import com.bootcamp.topic6.apptopic6.model.Product;
import java.util.List;

public interface ShoppingCartService {

  List<Item> getAllItems();
  boolean addToCart(Product product);
  Product getProductById(Long id);
  Item getItemByProductId(Long id);
  boolean deleteProductById(Long id);
  void clearCart();
  double doCheckOut();

}
