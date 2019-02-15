package com.bootcamp.topic6.apptopic6.service;

import com.bootcamp.topic6.apptopic6.dao.CartDAO;
import com.bootcamp.topic6.apptopic6.dao.CartItemDAO;
import com.bootcamp.topic6.apptopic6.dao.LineSaleDAO;
import com.bootcamp.topic6.apptopic6.dao.ProductDAO;
import com.bootcamp.topic6.apptopic6.dao.SaleDAO;
import com.bootcamp.topic6.apptopic6.model.Cart;
import com.bootcamp.topic6.apptopic6.model.CartItem;
import com.bootcamp.topic6.apptopic6.model.Product;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

  private final ProductDAO productDAO;
  private final SaleDAO saleDAO;
  private final LineSaleDAO lineSaleDAO;
  private final CartItemDAO cartItemDAO;

  private Cart cart;

  public ShoppingCartServiceImpl(ProductDAO productDAO,
      SaleDAO saleDAO, LineSaleDAO lineSaleDAO,
      CartItemDAO cartItemDAO, CartDAO cartDAO) {
    this.productDAO = productDAO;
    this.saleDAO = saleDAO;
    this.lineSaleDAO = lineSaleDAO;
    this.cartItemDAO = cartItemDAO;
    this.cart = cartDAO.save(new Cart());
  }

  @Override
  public List<CartItem> getAllCartItems() {
    return Collections.unmodifiableList(cart.getCartItemList());
  }

  @Override
  public boolean addToCart(Long idProduct, Integer quantity) {
    if (!productDAO.findById(idProduct).isPresent()) return false;

    CartItem newCartItem = new CartItem(cart,idProduct,quantity);
    cartItemDAO.save(newCartItem);

    return true;
  }

  @Override
  public Product getProductById(Long idProduct) {
    return null;
  }

  @Override
  public boolean removeProductById(Long id, Integer quantity) {
    return false;
  }

  @Override
  public boolean clearCart() {
    return false;
  }

  @Override
  public double doCheckOut() {
    return 0;
  }
}
