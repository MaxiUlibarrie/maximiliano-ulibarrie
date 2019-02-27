package com.bootcamp.shoppingcart.appshoppingcart.service;

import com.bootcamp.shoppingcart.appshoppingcart.exception.NotFoundException;
import com.bootcamp.shoppingcart.appshoppingcart.exception.RepeatedEntityException;
import com.bootcamp.shoppingcart.appshoppingcart.model.Role;
import com.bootcamp.shoppingcart.appshoppingcart.repository.RoleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements RoleService {

  @Autowired
  private RoleRepository roleRepo;

  private final String NAME_ROLE = "Role";

  @Override
  public List<Role> getAllRoles() {
    return roleRepo.findAll();
  }

  @Override
  public Role getRoleById(Long idrole) {
    return roleRepo.findById(idrole)
        .orElseThrow(() -> new NotFoundException(NAME_ROLE,idrole));
  }

  @Override
  public Role createRole(Role role) {
    if (roleRepo.exists(Example.of(role))) throw new RepeatedEntityException(NAME_ROLE);

    return roleRepo.save(role);
  }

  @Override
  public void deleteRole(Long idrole) {
    if (!roleRepo.existsById(idrole)) throw new NotFoundException(NAME_ROLE,idrole);
    roleRepo.deleteById(idrole);
  }
}
