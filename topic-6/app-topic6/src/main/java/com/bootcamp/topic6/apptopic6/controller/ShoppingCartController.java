package com.bootcamp.topic6.apptopic6.controller;

import com.bootcamp.topic6.apptopic6.model.CartItem;
import com.bootcamp.topic6.apptopic6.model.Sale;
import com.bootcamp.topic6.apptopic6.service.ShoppingCartService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shoppingcart")
public class ShoppingCartController {

  @Autowired
  private ShoppingCartService shoppingCartService;

  @PostMapping("/cart/{iduser}")
  public HttpStatus createCart(@PathVariable Long iduser) {
    boolean success = shoppingCartService.createCart(iduser);

    if (success) {
      return HttpStatus.CREATED;
    } else {
      return HttpStatus.NOT_FOUND;
    }
  }

  @GetMapping("/cart/{idcart}")
  public List<CartItem> getAllCartItems(@PathVariable Long idcart) {
    return shoppingCartService.getAllCartItems(idcart);
  }

  @PostMapping("/product/{idcart}")
  public HttpStatus addToCart(@PathVariable Long idcart,
                              @RequestParam Long idproduct,
                              @RequestParam Integer quantity) {

    boolean success = shoppingCartService.addToCart(idcart,idproduct,quantity);

    if (success) {
      return HttpStatus.CREATED;
    } else {
      return HttpStatus.NOT_FOUND;
    }
  }

  @PutMapping("/product/{idcart}")
  public HttpStatus removeProduct(@PathVariable Long idcart,
                                  @RequestParam Long idproduct,
                                  @RequestParam Integer quantity) {

    boolean success = shoppingCartService.removeProduct(idcart,idproduct,quantity);

    if (success) {
      return HttpStatus.NO_CONTENT;
    } else {
      return HttpStatus.NOT_FOUND;
    }
  }

  @DeleteMapping("/product/{idcart}")
  public HttpStatus deleteProductFromCart(@PathVariable Long idcart,
                                          @RequestParam Long idproduct) {
    boolean success = shoppingCartService.deleteProduct(idcart,idproduct);

    if (success) {
      return HttpStatus.NO_CONTENT;
    } else {
      return HttpStatus.NOT_FOUND;
    }
  }

  @DeleteMapping("/clear/{idcart}")
  public HttpStatus clearCart(@PathVariable Long idcart) {
    boolean success = shoppingCartService.clearCart(idcart);

    if (success) {
      return HttpStatus.NO_CONTENT;
    } else {
      return HttpStatus.NOT_FOUND;
    }
  }

  @GetMapping("/checkout/{idcart}")
  public Sale doCheckOut(@PathVariable Long idcart) {
    return shoppingCartService.doCheckOut(idcart);
  }

}
