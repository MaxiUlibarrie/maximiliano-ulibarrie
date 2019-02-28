package com.bootcamp.shoppingcart.appshoppingcart.service;

import com.bootcamp.shoppingcart.appshoppingcart.exception.NotFoundException;
import com.bootcamp.shoppingcart.appshoppingcart.exception.RepeatedEntityException;
import com.bootcamp.shoppingcart.appshoppingcart.model.Role;
import com.bootcamp.shoppingcart.appshoppingcart.model.User;
import com.bootcamp.shoppingcart.appshoppingcart.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

  @Autowired
  private UserRepository userRepo;

  @Autowired
  private RoleService roleService;

  private final String NAME_USER = "User";
  private final String MESSAGE_NOT_FOUND_USERNAME = "Could not find username.";
  private final String MESSAGE_NOT_HAVE_ROLE = "This user doesn't have the role.";

  @Override
  public List<User> getAllUsers() {
    return userRepo.findAll();
  }

  @Override
  public User createUser(User user) {
    if (userRepo.existsByUsername(user.getUsername()) || userRepo.existsByEmail(user.getEmail()))
      throw new RepeatedEntityException(NAME_USER);

    return userRepo.save(user);
  }

  @Override
  public User getUserById(Long iduser) {
    return userRepo.findById(iduser)
        .orElseThrow(() -> new NotFoundException(NAME_USER,iduser));
  }

  @Override
  public User getUserByUsername(String username) {
    return userRepo.findByUsername(username)
        .orElseThrow(() -> new NotFoundException(MESSAGE_NOT_FOUND_USERNAME));
  }

  @Override
  public void grantRoleToUser(Long iduser, Long idrole) {
    User user = getUserById(iduser);
    Role role = roleService.getRoleById(idrole);
    user.getRoleList().add(role);
    userRepo.save(user);
  }

  @Override
  public void removeRoleToUser(Long iduser, Long idrole) {
    User user = getUserById(iduser);
    boolean success = user.removeRole(idrole);
    if (!success) throw new RuntimeException(MESSAGE_NOT_HAVE_ROLE);

    userRepo.save(user);
  }

  @Override
  public void updateUser(Long iduser, User user) {
    userRepo.findById(iduser)
        .map(updatedUser -> {
          updatedUser.setUsername(user.getUsername());
          updatedUser.setEmail(user.getEmail());
          updatedUser.setPassword(user.getPassword());
          return userRepo.save(updatedUser);
        })
        .orElseThrow(() -> new NotFoundException(NAME_USER,iduser));
  }

  @Override
  public void deleteUserById(Long iduser) {
    if (!userRepo.existsById(iduser)) throw new NotFoundException(NAME_USER,iduser);
    userRepo.deleteById(iduser);
  }
}
