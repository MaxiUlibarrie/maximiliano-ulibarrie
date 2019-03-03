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
    return productService.getProductById(idproduct);
  }

  @GetMapping("/name/{name}")
  @ResponseStatus(HttpStatus.OK)
  public Product getProductByName(@PathVariable String name) {
    return productService.getProductByName(name);
  }

  @GetMapping("/category/{idcategory}")
  @ResponseStatus(HttpStatus.OK)
  public List<Product> getProductsByCategory(@PathVariable Long idcategory) {
    return productService.getProductsByCategory(idcategory);
  }

  @GetMapping("/category/{idcategory}/manufacturer/{manufacturer}")
  @ResponseStatus(HttpStatus.OK)
  public List<Product> getProductsByCategoryAndManufacturer(@PathVariable Long idcategory,
                                                            @PathVariable String manufacturer) {
    return productService.getProductsByCategoryAndManufacturer(idcategory,manufacturer);
  }

  @PostMapping("/category/{idcategory}")
  @ResponseStatus(HttpStatus.CREATED)
  public void createProduct(@RequestBody Product product,
                            @PathVariable Long idcategory) {
    productService.createProduct(product,idcategory);
  }

  @PutMapping("/{idproduct}")
  @ResponseStatus(HttpStatus.OK)
  public void updateProductPrice(@PathVariable Long idproduct,
                                 @RequestParam Double price) {
    productService.updatePriceProduct(idproduct,price);
  }

  @DeleteMapping("/{idproduct}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteProduct(@PathVariable Long idproduct) {
    productService.deleteProduct(idproduct);
  }
}