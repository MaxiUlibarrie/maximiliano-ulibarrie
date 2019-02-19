package com.bootcamp.topic6.apptopic6.service;

import com.bootcamp.topic6.apptopic6.model.Cart;
import com.bootcamp.topic6.apptopic6.model.CartItem;
import com.bootcamp.topic6.apptopic6.model.Sale;
import java.util.List;

public interface ShoppingCartService {

  Cart createCart(Long iduser);
  void deleteCart(Long idcart);
  List<CartItem> getAllCartItems(Long idcart);
  void addToCart(Long idcart, Long idproduct, Integer quantity);
  void removeProduct(Long idcart, Long idproduct, Integer quantity);
  void deleteProduct(Long idcart, Long idproduct);
  void clearCart(Long idcart);
  Sale doCheckOut(Long idcart);

}
