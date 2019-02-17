package com.bootcamp.topic6.apptopic6.repository;

import com.bootcamp.topic6.apptopic6.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
