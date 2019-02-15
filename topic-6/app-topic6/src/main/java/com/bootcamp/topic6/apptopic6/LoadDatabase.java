package com.bootcamp.topic6.apptopic6;

import com.bootcamp.topic6.apptopic6.dao.ProductDAO;
import com.bootcamp.topic6.apptopic6.model.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

  @Bean
  public CommandLineRunner initDatabase(ProductDAO productDAO) {
    return args -> {
      productDAO.save(new Product("tablet x",200));
      productDAO.save(new Product("phone x",234));
      productDAO.save(new Product("printer y",76));
      productDAO.save(new Product("phone z",98));
      productDAO.save(new Product("tablet g",67));
      productDAO.save(new Product("printer g",800));
    };
  }

}
