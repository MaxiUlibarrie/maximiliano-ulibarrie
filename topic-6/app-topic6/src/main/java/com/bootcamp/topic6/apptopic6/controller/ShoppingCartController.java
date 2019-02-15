package com.bootcamp.topic6.apptopic6.controller;

import com.bootcamp.topic6.apptopic6.dao.CartDAO;
import com.bootcamp.topic6.apptopic6.dao.CartItemDAO;
import com.bootcamp.topic6.apptopic6.dao.LineSaleDAO;
import com.bootcamp.topic6.apptopic6.dao.ProductDAO;
import com.bootcamp.topic6.apptopic6.dao.SaleDAO;
import com.bootcamp.topic6.apptopic6.model.CartItem;
import com.bootcamp.topic6.apptopic6.service.ShoppingCartService;
import com.bootcamp.topic6.apptopic6.service.ShoppingCartServiceImpl;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCartController {

  private final CartDAO cartDAO;
  private final ProductDAO productDAO;
  private final SaleDAO saleDAO;
  private final LineSaleDAO lineSaleDAO;
  private final CartItemDAO cartItemDAO;

  private final ShoppingCartService shoppingCartService;

  public ShoppingCartController(CartDAO cartDAO,
      ProductDAO productDAO, SaleDAO saleDAO,
      LineSaleDAO lineSaleDAO, CartItemDAO cartItemDAO) {
    this.cartDAO = cartDAO;
    this.productDAO = productDAO;
    this.saleDAO = saleDAO;
    this.lineSaleDAO = lineSaleDAO;
    this.cartItemDAO = cartItemDAO;
    this.shoppingCartService =
        new ShoppingCartServiceImpl(productDAO,saleDAO,lineSaleDAO,cartItemDAO,cartDAO);
  }

  @GetMapping("/cartitems")
  public List<CartItem> getAllCartItems() {
    return shoppingCartService.getAllCartItems();
  }

  @PostMapping("/addproduct/{idproduct}/{quantity}")
  public HttpStatus addToCart(@PathVariable Long idproduct,
                              @PathVariable Integer quantity) {
    boolean success = shoppingCartService.addToCart(idproduct,quantity);

    if (success) {
      return HttpStatus.CREATED;
    } else {
      return HttpStatus.CONFLICT;
    }
  }
}
