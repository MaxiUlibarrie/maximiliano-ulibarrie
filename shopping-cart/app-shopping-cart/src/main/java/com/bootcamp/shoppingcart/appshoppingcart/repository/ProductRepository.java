package com.bootcamp.shoppingcart.appshoppingcart.repository;

import com.bootcamp.shoppingcart.appshoppingcart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

}
