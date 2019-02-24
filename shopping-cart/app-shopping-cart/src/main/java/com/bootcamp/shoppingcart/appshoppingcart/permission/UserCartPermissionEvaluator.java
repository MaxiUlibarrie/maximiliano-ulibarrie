package com.bootcamp.shoppingcart.appshoppingcart.permission;

import com.bootcamp.shoppingcart.appshoppingcart.model.Cart;
import com.bootcamp.shoppingcart.appshoppingcart.model.User;
import com.bootcamp.shoppingcart.appshoppingcart.repository.CartRepository;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserCartPermissionEvaluator implements PermissionEvaluator {

  @Autowired
  private CartRepository cartRepo;

  @Override
  public boolean hasPermission(Authentication authentication, Object o, Object o1) {

    throw new UnsupportedOperationException();
  }

  @Override
  public boolean hasPermission(Authentication auth, Serializable idcart,
      String targetType, Object permission) {

    Cart cart = cartRepo.findById((Long) idcart)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
            String.format("Could not find %s: %d.",targetType,(Long) idcart)));

    boolean permissionGranted = cart.getUser().getUsername().equals(auth.getName());

    return permissionGranted;
  }
}
