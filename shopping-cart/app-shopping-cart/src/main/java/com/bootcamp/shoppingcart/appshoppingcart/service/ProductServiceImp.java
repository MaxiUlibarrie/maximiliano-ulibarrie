package com.bootcamp.shoppingcart.appshoppingcart.service;

import com.bootcamp.shoppingcart.appshoppingcart.exception.NotFoundException;
import com.bootcamp.shoppingcart.appshoppingcart.exception.RepeatedEntityException;
import com.bootcamp.shoppingcart.appshoppingcart.model.Category;
import com.bootcamp.shoppingcart.appshoppingcart.model.Product;
import com.bootcamp.shoppingcart.appshoppingcart.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImp implements ProductService {

  @Autowired
  private ProductRepository productRepo;

  @Autowired
  private CategoryService categoryService;

  private final String NAME_PRODUCT = "Product";

  @Override
  public List<Product> getAllProducts() {
    return productRepo.findAll();
  }

  @Override
  public Product getProductById(Long idproduct) {
    return productRepo.findById(idproduct)
        .orElseThrow(() -> new NotFoundException(NAME_PRODUCT,idproduct));
  }

  @Override
  public Product getProductByName(String name) {
    return productRepo.findByName(name)
        .orElseThrow(() -> new NotFoundException(name));
  }

  @Override
  public List<Product> getProductsByCategory(Long idcategory) {
    Category category = categoryService.getCategoryById(idcategory);
    return productRepo.findByCategory(category);
  }

  @Override
  public List<Product> getProductsByCategoryAndManufacturer(Long idcategory,
      String manufacturer) {
    Category category = categoryService.getCategoryById(idcategory);

    return productRepo.findByCategoryAndManufacturer(category,manufacturer);
  }

  @Override
  public Product createProduct(Product product, Long idcategory) {
    Category category = categoryService.getCategoryById(idcategory);
    product.setCategory(category);

    if (productRepo.exists(Example.of(product)))
      throw new RepeatedEntityException(NAME_PRODUCT);

    return productRepo.save(product);
  }

  @Override
  public void updatePriceProduct(Long idproduct, Double price) {
    Product product = getProductById(idproduct);
    product.setPrice(price);
    productRepo.save(product);
  }

  @Override
  public void deleteProduct(Long idproduct) {
    if (!productRepo.existsById(idproduct))
      throw new NotFoundException(NAME_PRODUCT,idproduct);

    productRepo.deleteById(idproduct);
  }
}
