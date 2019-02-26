package com.bootcamp.shoppingcart.appshoppingcart.controller;

import com.bootcamp.shoppingcart.appshoppingcart.model.User;
import com.bootcamp.shoppingcart.appshoppingcart.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/{iduser}")
  @ResponseStatus(HttpStatus.OK)
  public User getUserById(@PathVariable Long iduser) {
    try {
      return userService.getUserById(iduser);
    } catch (RuntimeException re) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,re.getMessage());
    }
  }

  @GetMapping("/{username}")
  @ResponseStatus(HttpStatus.OK)
  public User getUserById(@PathVariable String username) {
    try {
      return userService.getUserByUsername(username);
    } catch (RuntimeException re) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,re.getMessage());
    }
  }

  @PutMapping("/{iduser}/role/{idrole}")
  @ResponseStatus(HttpStatus.OK)
  public void grantRoleToUser(@PathVariable Long iduser,
                              @PathVariable Long idrole) {
    try {
      userService.grantRoleToUser(iduser,idrole);
    } catch (RuntimeException re) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,re.getMessage());
    }
  }

  @PutMapping("/{iduser}")
  @ResponseStatus(HttpStatus.OK)
  public void updateUser(@PathVariable Long iduser,
                         @RequestBody User user) {
    try {
      userService.updateUser(iduser,user);
    } catch (RuntimeException re) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,re.getMessage());
    }
  }

  @DeleteMapping("/{iduser}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteUser(@PathVariable Long iduser) {
    try {
      userService.deleteUserById(iduser);
    } catch (RuntimeException re) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,re.getMessage());
    }
  }
}