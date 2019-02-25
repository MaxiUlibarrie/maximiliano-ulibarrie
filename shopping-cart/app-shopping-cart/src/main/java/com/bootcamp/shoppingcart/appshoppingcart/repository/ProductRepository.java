package com.bootcamp.shoppingcart.appshoppingcart.repository;

import com.bootcamp.shoppingcart.appshoppingcart.model.Category;
import com.bootcamp.shoppingcart.appshoppingcart.model.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

  Optional<Product> findByName(String name);
  List<Product> findByCategory(Category category);
  List<Product> findByCategoryAndManufacturer(Category category, String manufacturer);
}
