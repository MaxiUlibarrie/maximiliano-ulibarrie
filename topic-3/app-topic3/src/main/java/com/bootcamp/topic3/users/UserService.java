package com.bootcamp.topic3.users;

public interface UserService {

  /**
   * creates a new user.
   * @param user
   */
  public void createUser(User user);

  /**
   * updates the user's name.
   * @param idUser
   * @param name
   */
  public void updateUserNameById(Long idUser, String name);

  /**
   * updates the user's email.
   * @param idUser
   * @param email
   */
  public void updateUserEmailById(Long idUser, String email);

  /**
   * updates the user's password.
   * @param idUser
   * @param password
   */
  public void updateUserPasswordById(Long idUser, String password);

  /**
   * returns user by id.
   * @param idUser
   * @return
   */
  public User getUserById(Long idUser);

  /**
   * deletes user by id.
   * @param idUser
   */
  public void deleteUserById(Long idUser);
}
