package com.bootcamp.shoppingcart.appshoppingcart.config;

import com.bootcamp.shoppingcart.appshoppingcart.model.User;
import com.bootcamp.shoppingcart.appshoppingcart.repository.ProductRepository;
import com.bootcamp.shoppingcart.appshoppingcart.model.Product;
import com.bootcamp.shoppingcart.appshoppingcart.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

  @Bean
  public CommandLineRunner initDatabase(ProductRepository productRepo, UserRepository userRepo) {
    return args -> {
      productRepo.save(new Product("tablet x",200));
      productRepo.save(new Product("phone x",234));
      productRepo.save(new Product("printer y",76));
      productRepo.save(new Product("phone z",98));
      productRepo.save(new Product("tablet g",67));
      productRepo.save(new Product("printer g",800));

      userRepo.save(new User("Roberto","qwe","rgs@gmail.com"));
      userRepo.save(new User("Marcelo","asd","mm@gmail.com"));
      userRepo.save(new User("Cristina","zxc","cm@gmail.com"));
    };
  }

}
