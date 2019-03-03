package com.bootcamp.shoppingcart.appshoppingcart.config;

import com.bootcamp.shoppingcart.appshoppingcart.model.Category;
import com.bootcamp.shoppingcart.appshoppingcart.model.Product;
import com.bootcamp.shoppingcart.appshoppingcart.model.Role;
import com.bootcamp.shoppingcart.appshoppingcart.model.User;
import com.bootcamp.shoppingcart.appshoppingcart.repository.CategoryRepository;
import com.bootcamp.shoppingcart.appshoppingcart.repository.ProductRepository;
import com.bootcamp.shoppingcart.appshoppingcart.repository.RoleRepository;
import com.bootcamp.shoppingcart.appshoppingcart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

  @Autowired
  private ProductRepository productRepo;

  @Autowired
  private CategoryRepository categoryRepo;

  @Autowired
  private UserRepository userRepo;

  @Autowired
  private RoleRepository roleRepo;

  @Bean
  public CommandLineRunner initDatabase() {
    return args -> {
      Category category1 = new Category("Electronic","Electronic");
      Category category2 = new Category("Office","Office");

      categoryRepo.save(category1);
      categoryRepo.save(category2);

      productRepo.save(new Product("tablet x",200,category1,"Samsung"));
      productRepo.save(new Product("phone x",234,category1,"Samsung"));
      productRepo.save(new Product("printer y",76,category2,"HP"));
      productRepo.save(new Product("phone z",98,category1,"Motorola"));
      productRepo.save(new Product("tablet g",67,category1,"Samsung"));
      productRepo.save(new Product("printer g",800,category2,"HP"));

      User user1 = new User("joaquin","joaquin","joaquin@gmail.com");
      User user2 = new User("agostina","agostina","agostina@gmail.com");

      userRepo.save(user1);
      userRepo.save(user2);

      roleRepo.save(new Role("ADMIN"));
      roleRepo.save(new Role("EMPLOYEE"));

      Role roleAdmin = roleRepo.findByName("ADMIN").get();
      Role roleEmployee = roleRepo.findByName("EMPLOYEE").get();

      user1.getRoleList().add(roleAdmin);
      user2.getRoleList().add(roleEmployee);

      userRepo.save(user1);
      userRepo.save(user2);
    };
  }
}
