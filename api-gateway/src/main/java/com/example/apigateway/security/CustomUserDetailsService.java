package com.example.apigateway.security;

import com.example.apigateway.feignInterface.AuthClientInterface;
import com.example.commonservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUserDetailsService implements UserDetailsService {

  private AuthClientInterface authClientInterface;

  public CustomUserDetailsService(AuthClientInterface authClientInterface) {
    this.authClientInterface = authClientInterface;
  }

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    User user = authClientInterface.findUserByEmail(s);

    return new org.springframework.security.core.userdetails.User(String.join("-", s, user.getEmail()),
      user.getPassword(), true, true, true, true, new ArrayList<>());
  }
}
