package com.bootcamp.shoppingcart.appshoppingcart.permission;

import com.bootcamp.shoppingcart.appshoppingcart.model.Cart;
import com.bootcamp.shoppingcart.appshoppingcart.service.ShoppingCartService;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class UserCartPermissionEvaluator implements PermissionEvaluator {

  @Autowired
  private ShoppingCartService shoppingCartService;

  @Override
  public boolean hasPermission(Authentication authentication, Object o, Object o1) {

    throw new UnsupportedOperationException();
  }

  @Override
  public boolean hasPermission(Authentication auth, Serializable idcart,
      String targetType, Object permission) {

    Cart cart = shoppingCartService.getCartById((Long) idcart);

    boolean permissionGranted = cart.getUser().getUsername().equals(auth.getName());

    return permissionGranted;
  }
}
