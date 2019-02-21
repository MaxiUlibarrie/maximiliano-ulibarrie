package com.bootcamp.shoppingcart.appshoppingcart.controller;

import com.bootcamp.shoppingcart.appshoppingcart.model.Cart;
import com.bootcamp.shoppingcart.appshoppingcart.model.CartItem;
import com.bootcamp.shoppingcart.appshoppingcart.model.Sale;
import com.bootcamp.shoppingcart.appshoppingcart.service.ShoppingCartService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/shoppingcart")
public class ShoppingCartController {

  @Autowired
  private ShoppingCartService shoppingCartService;

  @PostMapping("/newcart/{iduser}")
  @ResponseStatus(HttpStatus.CREATED)
  public Cart createCart(@PathVariable Long iduser) {
    try {
      return shoppingCartService.createCart(iduser);
    } catch (RuntimeException re) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,re.getMessage());
    }
  }

  @DeleteMapping("/deletecart/{idcart}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCart(@PathVariable Long idcart) {
    try {
      shoppingCartService.deleteCart(idcart);
    } catch (RuntimeException re) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,re.getMessage());
    }
  }

  @GetMapping("/showcart/{idcart}")
  @ResponseStatus(HttpStatus.OK)
  public List<CartItem> getAllCartItems(@PathVariable Long idcart) {
    try {
      return shoppingCartService.getAllCartItems(idcart);
    } catch (RuntimeException re) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,re.getMessage());
    }
  }

  @PutMapping("/addproduct/{idcart}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void addToCart(@PathVariable Long idcart,
                        @RequestParam Long idproduct,
                        @RequestParam Integer quantity) {
    try {
      shoppingCartService.addToCart(idcart,idproduct,quantity);
    } catch (RuntimeException re) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,re.getMessage());
    }
  }

  @PutMapping("/removeproduct/{idcart}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void removeProduct(@PathVariable Long idcart,
                            @RequestParam Long idproduct,
                            @RequestParam Integer quantity) {
    try {
      shoppingCartService.removeProduct(idcart,idproduct,quantity);
    } catch (RuntimeException re) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,re.getMessage());
    }
  }

  @DeleteMapping("/deleteproduct/{idcart}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteProductFromCart(@PathVariable Long idcart,
                                    @RequestParam Long idproduct) {
    try {
      shoppingCartService.deleteProduct(idcart,idproduct);
    } catch (RuntimeException re) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,re.getMessage());
    }
  }

  @DeleteMapping("/clear/{idcart}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void clearCart(@PathVariable Long idcart) {
    try {
      shoppingCartService.clearCart(idcart);
    } catch (RuntimeException re) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,re.getMessage());
    }
  }

  @PostMapping("/checkout/{idcart}")
  @ResponseStatus(HttpStatus.CREATED)
  public Sale doCheckOut(@PathVariable Long idcart) {
    try {
      return shoppingCartService.doCheckOut(idcart);
    } catch (RuntimeException re) {
      throw new ResponseStatusException(HttpStatus.CONFLICT,re.getMessage());
    }
  }

}