package com.example.apigateway.security;

import com.example.apigateway.feign.AuthServiceClient;
import com.example.commonservice.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUserDetailService implements UserDetailsService {

  private final AuthServiceClient authServiceClient;

  public CustomUserDetailService(AuthServiceClient authServiceClient) {
    this.authServiceClient = authServiceClient;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = authServiceClient.getUserByEmail(email);

    if (user == null) {
      throw new UsernameNotFoundException("Username:" + email + " not found");
    }
    return new org.springframework.security.core.userdetails.User(String.join("-", email, email), user.getPassword(),
      true, true, true, true, new ArrayList<>());
  }
}
