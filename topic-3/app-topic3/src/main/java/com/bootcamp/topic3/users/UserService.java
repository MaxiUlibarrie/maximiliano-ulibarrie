package com.bootcamp.topic3.users;

public interface UserService {

  /**
   * Creates a new user.
   * @param user
   */
  public void createUser(User user);

  /**
   * Updates the user's name.
   * @param idUser
   * @param name
   */
  public void updateUserNameById(Long idUser, String name);

  /**
   * Updates the user's email.
   * @param idUser
   * @param email
   */
  public void updateUserEmailById(Long idUser, String email);

  /**
   * Updates the user's password.
   * @param idUser
   * @param password
   */
  public void updateUserPasswordById(Long idUser, String password);

  /**
   * Returns user by id.
   * @param idUser
   * @return
   */
  public User getUserById(Long idUser);

  /**
   * Deletes user by id.
   * @param idUser
   */
  public void deleteUserById(Long idUser);
}
