package com.bootcamp.topic6.apptopic6.controller;

import com.bootcamp.topic6.apptopic6.model.User;
import com.bootcamp.topic6.apptopic6.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/users")
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/user/{iduser}")
  public User getUserById(@PathVariable Long iduser) {
    return userService.getUserById(iduser);
  }

}
