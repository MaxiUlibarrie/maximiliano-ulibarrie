package com.bootcamp.shoppingcart.appshoppingcart.service;

import com.bootcamp.shoppingcart.appshoppingcart.model.CustomUserDetails;
import com.bootcamp.shoppingcart.appshoppingcart.model.User;
import com.bootcamp.shoppingcart.appshoppingcart.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customUserDetailService")
public class CustomUserDetailService implements UserDetailsService {

  @Autowired
  private UserRepository userRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> optionalUser = userRepo.findByUsername(username);

    return optionalUser
        .map(CustomUserDetails::new)
        .orElseThrow(() -> new UsernameNotFoundException("Could not find username."));
  }
}
