package com.bootcamp.topic6.apptopic6.dao;

import com.bootcamp.topic6.apptopic6.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleDAO extends JpaRepository<Sale,Long> {

}
