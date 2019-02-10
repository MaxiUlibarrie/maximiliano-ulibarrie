package com.bootcamp.topic3.users;

import java.util.List;

public class UserServiceImpl implements UserService {

  private List<User> users;

  @Override
  public void createUser(User user) {
    users.add(user);
  }

  @Override
  public void updateUserNameById(Long idUser, String name) {
    getUserById(idUser).setName(name);
  }

  @Override
  public void updateUserEmailById(Long idUser, String email) {
    getUserById(idUser).setEmail(email);
  }

  @Override
  public void updateUserPasswordById(Long idUser, String password) {
    getUserById(idUser).setPassword(password);
  }

  @Override
  public User getUserById(Long idUser) {
    for (User user : users) {
      if (user.getId() == idUser) {
        return user;
      }
    }
    return null;
  }

  @Override
  public void deleteUserById(Long idUser) {
    users.removeIf(user -> (user.getId() == idUser));
  }


}
