package com.bootcamp.shoppingcart.appshoppingcart.repository;

import com.bootcamp.shoppingcart.appshoppingcart.model.LineSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineSaleRepository extends JpaRepository<LineSale,Long> {

}
