package com.bootcamp.topic6.apptopic6.service;

import com.bootcamp.topic6.apptopic6.model.CartItem;
import com.bootcamp.topic6.apptopic6.model.Sale;
import java.util.List;

public interface ShoppingCartService {

  boolean createCart(Long iduser);
  List<CartItem> getAllCartItems(Long idcart);
  boolean addToCart(Long idcart, Long idproduct, Integer quantity);
  boolean removeProduct(Long idcart, Long idproduct, Integer quantity);
  boolean deleteProduct(Long idcart, Long idproduct);
  boolean clearCart(Long idcart);
  Sale doCheckOut(Long idcart);

}
