package com.bootcamp.shoppingcart.appshoppingcart.controller;

import com.bootcamp.shoppingcart.appshoppingcart.model.Cart;
import com.bootcamp.shoppingcart.appshoppingcart.model.CartItem;
import com.bootcamp.shoppingcart.appshoppingcart.model.Sale;
import com.bootcamp.shoppingcart.appshoppingcart.service.ShoppingCartService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
@RequestMapping("/api/shoppingcart")
public class ShoppingCartController {

  @Autowired
  private ShoppingCartService shoppingCartService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Cart createCart(Authentication auth) {
    try {
      return shoppingCartService.createCart(auth.getName());
    } catch (RuntimeException re) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,re.getMessage());
    }
  }

  @DeleteMapping("/{idcart}")
  @PreAuthorize("hasPermission(#idcart,'Cart','delete')")
  @ResponseStatus(HttpStatus.OK)
  public void deleteCart(@PathVariable Long idcart) {
    try {
      shoppingCartService.deleteCart(idcart);
    } catch (RuntimeException re) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,re.getMessage());
    }
  }

  @GetMapping("/{idcart}")
  @PreAuthorize("hasPermission(#idcart,'Cart','read')")
  @ResponseStatus(HttpStatus.OK)
  public List<CartItem> getAllCartItems(@PathVariable Long idcart) {
    try {
      return shoppingCartService.getAllCartItems(idcart);
    } catch (RuntimeException re) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,re.getMessage());
    }
  }

  @PostMapping("/{idcart}/product/{idproduct}")
  @PreAuthorize("hasPermission(#idcart,'Cart','create')")
  @ResponseStatus(HttpStatus.OK)
  public void addToCart(@PathVariable Long idcart,
                        @PathVariable Long idproduct,
                        @RequestParam Integer quantity) {
    try {
      shoppingCartService.addToCart(idcart,idproduct,quantity);
    } catch (RuntimeException re) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,re.getMessage());
    }
  }

  @PutMapping("/{idcart}/product/{idproduct}")
  @PreAuthorize("hasPermission(#idcart,'Cart','update')")
  @ResponseStatus(HttpStatus.OK)
  public void removeProduct(@PathVariable Long idcart,
                            @PathVariable Long idproduct,
                            @RequestParam Integer quantity) {
    try {
      shoppingCartService.removeProduct(idcart,idproduct,quantity);
    } catch (RuntimeException re) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,re.getMessage());
    }
  }

  @DeleteMapping("/{idcart}/product/{idproduct}")
  @PreAuthorize("hasPermission(#idcart,'Cart','delete')")
  @ResponseStatus(HttpStatus.OK)
  public void deleteProductFromCart(@PathVariable Long idcart,
                                    @PathVariable Long idproduct) {
    try {
      shoppingCartService.deleteProduct(idcart,idproduct);
    } catch (RuntimeException re) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,re.getMessage());
    }
  }

  @DeleteMapping("/clear/{idcart}")
  @PreAuthorize("hasPermission(#idcart,'Cart','delete')")
  @ResponseStatus(HttpStatus.OK)
  public void clearCart(@PathVariable Long idcart) {
    try {
      shoppingCartService.clearCart(idcart);
    } catch (RuntimeException re) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,re.getMessage());
    }
  }

  @PostMapping("/checkout/{idcart}")
  @PreAuthorize("hasPermission(#idcart,'Cart','create')")
  @ResponseStatus(HttpStatus.CREATED)
  public Sale doCheckOut(@PathVariable Long idcart) {
    try {
      return shoppingCartService.doCheckOut(idcart);
    } catch (RuntimeException re) {
      throw new ResponseStatusException(HttpStatus.CONFLICT,re.getMessage());
    }
  }

}