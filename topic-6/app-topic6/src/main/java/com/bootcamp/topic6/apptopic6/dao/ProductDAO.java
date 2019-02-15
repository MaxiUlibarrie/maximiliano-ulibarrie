package com.bootcamp.topic6.apptopic6.dao;

import com.bootcamp.topic6.apptopic6.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDAO extends JpaRepository<Product,Long> {

}
