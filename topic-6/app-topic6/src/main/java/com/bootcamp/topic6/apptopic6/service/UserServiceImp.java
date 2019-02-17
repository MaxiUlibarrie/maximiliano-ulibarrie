package com.bootcamp.topic6.apptopic6.service;

import com.bootcamp.topic6.apptopic6.model.User;
import com.bootcamp.topic6.apptopic6.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImp implements UserService {

  private final UserRepository userRepo;

  @Autowired
  public UserServiceImp(UserRepository userRepo) {
    this.userRepo = userRepo;
  }

  @Override
  public List<User> getAllUsers() {
    return userRepo.findAll();
  }

  @Override
  public User createUser(String username, String password, String email) {
    User newUser = new User(username,password,email);

    return userRepo.save(newUser);
  }

  @Override
  public User getUserById(Long iduser) {
    return userRepo.findById(iduser).get();
  }

  @Override
  public void updateUsernameById(Long iduser, String newUsername) {
    User user = userRepo.findById(iduser).get();
    user.setUsername(newUsername);
    userRepo.save(user);
  }

  @Override
  public void updateUserPasswordById(Long iduser, String newPassword) {
    User user = userRepo.findById(iduser).get();
    user.setPassword(newPassword);
    userRepo.save(user);
  }

  @Override
  public void updateUserEmailById(Long iduser, String newEmail) {
    User user = userRepo.findById(iduser).get();
    user.setEmail(newEmail);
    userRepo.save(user);
  }

  @Override
  public void deleteUserById(Long iduser) {
    userRepo.deleteById(iduser);
  }
}
