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
    return userService.getUserById(iduser);
  }

  @GetMapping("/username/{username}")
  @ResponseStatus(HttpStatus.OK)
  public User getUserById(@PathVariable String username) {
    return userService.getUserByUsername(username);
  }

  @PutMapping("/{iduser}/role/{idrole}")
  @ResponseStatus(HttpStatus.OK)
  public void grantRoleToUser(@PathVariable Long iduser,
                              @PathVariable Long idrole) {
    userService.grantRoleToUser(iduser,idrole);
  }

  @DeleteMapping("/{iduser}/role/{idrole}")
  @ResponseStatus(HttpStatus.OK)
  public void removeRoleToUser(@PathVariable Long iduser,
                               @PathVariable Long idrole) {
    userService.removeRoleToUser(iduser,idrole);
  }

  @PutMapping("/{iduser}")
  @ResponseStatus(HttpStatus.OK)
  public void updateUser(@PathVariable Long iduser,
                         @RequestBody User user) {
    userService.updateUser(iduser,user);
  }

  @DeleteMapping("/{iduser}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteUser(@PathVariable Long iduser) {
    userService.deleteUserById(iduser);
  }
}