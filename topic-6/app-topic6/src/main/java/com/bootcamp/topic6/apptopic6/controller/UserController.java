package com.bootcamp.topic6.apptopic6.controller;

import com.bootcamp.topic6.apptopic6.model.User;
import com.bootcamp.topic6.apptopic6.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/{iduser}")
  public User getUserById(@PathVariable Long iduser) {
    return userService.getUserById(iduser);
  }

}
