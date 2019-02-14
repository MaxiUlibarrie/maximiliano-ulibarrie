package com.bootcamp.topic6.apptopic6.controller;

import com.bootcamp.topic6.apptopic6.model.Product;
import com.bootcamp.topic6.apptopic6.repository.SaleRepository;
import com.bootcamp.topic6.apptopic6.service.Item;
import com.bootcamp.topic6.apptopic6.service.ShoppingCartService;
import com.bootcamp.topic6.apptopic6.service.ShoppingCartServiceImpl;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCartController {

  private final SaleRepository saleRepository;
  private final ShoppingCartService shoppingCartService;

  public ShoppingCartController(
      SaleRepository saleRepository) {
    this.saleRepository = saleRepository;
    this.shoppingCartService = new ShoppingCartServiceImpl(saleRepository);
  }

  @GetMapping("/items")
  public List<Item> getAllItems() {
    return shoppingCartService.getAllItems();
  }

  @PostMapping("/items")
  public Product addToCart(@RequestBody Product newProduct) {
    shoppingCartService.addToCart(newProduct);
    return newProduct;
  }

}
