package com.bootcamp.shoppingcart.appshoppingcart.service;

import com.bootcamp.shoppingcart.appshoppingcart.model.Role;
import java.util.List;

public interface RoleService {

  List<Role> getAllRoles();
  Role getRoleById(Long idrole);
  Role createRole(Role role);
  void updateRole(Long idrole, Role role);
  void deleteRole(Long idrole);
}
