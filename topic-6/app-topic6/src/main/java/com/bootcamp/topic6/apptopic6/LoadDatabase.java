package com.bootcamp.topic6.apptopic6;

import com.bootcamp.topic6.apptopic6.model.User;
import com.bootcamp.topic6.apptopic6.repository.ProductRepository;
import com.bootcamp.topic6.apptopic6.model.Product;
import com.bootcamp.topic6.apptopic6.repository.UserRepository;
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
