package com.bootcamp.shoppingcart.appshoppingcart.controller;

import com.bootcamp.shoppingcart.appshoppingcart.model.Role;
import com.bootcamp.shoppingcart.appshoppingcart.service.RoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/role")
public class RoleController {

  @Autowired
  private RoleService roleService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Role> getAllRoles() {
    return roleService.getAllRoles();
  }

  @GetMapping("/{idrole}")
  @ResponseStatus(HttpStatus.OK)
  public Role getRoleById(@PathVariable Long idrole) {
    try {
      return roleService.getRoleById(idrole);
    } catch (RuntimeException re) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,re.getMessage());
    }
  }

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public void createRole(@RequestBody Role role) {
    try {
      roleService.createRole(role);
    } catch (RuntimeException re) {
      throw new ResponseStatusException(HttpStatus.CONFLICT,re.getMessage());
    }
  }

  @DeleteMapping("/{idrole}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteRole(@PathVariable Long idrole) {
    try {
      roleService.deleteRole(idrole);
    } catch (RuntimeException re) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,re.getMessage());
    }
  }
}