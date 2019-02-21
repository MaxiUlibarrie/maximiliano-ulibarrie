package com.bootcamp.shoppingcart.appshoppingcart.service;

import com.bootcamp.shoppingcart.appshoppingcart.model.User;
import java.util.List;

public interface UserService {

  List<User> getAllUsers();
  User createUser(String username, String password, String email);
  User getUserById(Long iduser);
  void updateUsernameById(Long iduser, String newUsername);
  void updateUserPasswordById(Long iduser, String newPassword);
  void updateUserEmailById(Long iduser, String newEmail);
  void deleteUserById(Long iduser);

}
