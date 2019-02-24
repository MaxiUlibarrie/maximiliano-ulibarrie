package com.bootcamp.shoppingcart.appshoppingcart.service;

import com.bootcamp.shoppingcart.appshoppingcart.model.Cart;
import com.bootcamp.shoppingcart.appshoppingcart.model.CartItem;
import com.bootcamp.shoppingcart.appshoppingcart.model.Sale;
import java.util.List;

public interface ShoppingCartService {

  Cart createCart(String username);
  void deleteCart(Long idcart);
  List<CartItem> getAllCartItems(Long idcart);
  void addToCart(Long idcart, Long idproduct, Integer quantity);
  void removeProduct(Long idcart, Long idproduct, Integer quantity);
  void deleteProduct(Long idcart, Long idproduct);
  void clearCart(Long idcart);
  Sale doCheckOut(Long idcart);
}
