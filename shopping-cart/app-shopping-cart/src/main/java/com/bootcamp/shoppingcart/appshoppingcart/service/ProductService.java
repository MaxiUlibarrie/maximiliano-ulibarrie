package com.bootcamp.shoppingcart.appshoppingcart.service;

import com.bootcamp.shoppingcart.appshoppingcart.model.Product;
import java.util.List;

public interface ProductService {

  List<Product> getAllProducts();
  Product getProductById(Long idproduct);
  Product getProductByName(String name);
  List<Product> getProductsByCategory(Long idcategory);
  List<Product> getProductsByCategoryAndManufacturer(Long idcategory, String manufacturer);
  Product createProduct(Product product);
  void updatePriceProduct(Long idproduct, Double price);
  void deleteProduct(Long idproduct);
}
