package com.bootcamp.shoppingcart.appshoppingcart.service;

import com.bootcamp.shoppingcart.appshoppingcart.model.User;
import java.util.List;

public interface UserService {

  List<User> getAllUsers();
  User createUser(User user);
  User getUserById(Long iduser);
  User getUserByUsername(String username);
  void grantRoleToUser(Long iduser, Long idrole);
  void removeRoleToUser(Long iduser, Long idrole);
  void updateUser(Long iduser, User user);
  void deleteUserById(Long iduser);
}
