package com.bootcamp.topic6.apptopic6.controller;

import com.bootcamp.topic6.apptopic6.dao.ProductDAO;
import com.bootcamp.topic6.apptopic6.model.Product;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

  private final ProductDAO productDAO;

  public ProductController(ProductDAO productDAO) {
    this.productDAO = productDAO;
  }

  @GetMapping("/products")
  public List<Product> getAllProducts() {
    return productDAO.findAll();
  }
}
