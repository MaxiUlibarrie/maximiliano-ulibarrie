package com.bootcamp.shoppingcart.appshoppingcart.controller;

import com.bootcamp.shoppingcart.appshoppingcart.model.Product;
import com.bootcamp.shoppingcart.appshoppingcart.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/product")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Product> getAllProducts() {
    return productService.getAllProducts();
  }

  @GetMapping("/{idproduct}")
  @ResponseStatus(HttpStatus.OK)
  public Product getProductById(@PathVariable Long idproduct) {
    try {
      return productService.getProductById(idproduct);
    } catch (RuntimeException re) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,re.getMessage());
    }
  }

  @GetMapping("/{nameproduct}")
  @ResponseStatus(HttpStatus.OK)
  public Product getProductById(@PathVariable String nameproduct) {
    try {
      return productService.getProductByName(nameproduct);
    } catch (RuntimeException re) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,re.getMessage());
    }
  }

  @GetMapping("/category/{idcategory}")
  @ResponseStatus(HttpStatus.OK)
  public List<Product> getProductsByCategory(@PathVariable Long idcategory) {
    try {
      return productService.getProductsByCategory(idcategory);
    } catch (RuntimeException re) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,re.getMessage());
    }
  }

  @GetMapping("/category/{idcategory}/manufacturer/{manufacturer}")
  @ResponseStatus(HttpStatus.OK)
  public List<Product> getProductsByCategoryAndManufacturer(@PathVariable Long idcategory,
                                                            @PathVariable String manufacturer) {
    try {
      return productService.getProductsByCategoryAndManufacturer(idcategory,manufacturer);
    } catch (RuntimeException re) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,re.getMessage());
    }
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createProduct(@RequestBody Product product) {
    productService.createProduct(product);
  }

  @PutMapping("/{idproduct}")
  @ResponseStatus(HttpStatus.OK)
  public void updateProductPrice(@PathVariable Long idproduct,
                                 @RequestParam Double price) {
    try {
      productService.updatePriceProduct(idproduct,price);
    } catch (RuntimeException re) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,re.getMessage());
    }
  }

  @DeleteMapping("/{idproduct}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteProduct(@PathVariable Long idproduct) {
    try {
      productService.deleteProduct(idproduct);
    } catch (RuntimeException re) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,re.getMessage());
    }
  }
}