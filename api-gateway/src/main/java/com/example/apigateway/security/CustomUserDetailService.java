package com.example.apigateway.security;

import com.example.apigateway.feign.AuthServiceClient;
import com.example.commonservice.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
    List<String> roles = new ArrayList<>();
    roles.add("ADMIN");
    List<GrantedAuthority> grantedAuthorities = roles.stream().map(SimpleGrantedAuthority::new)
      .collect(Collectors.toList());
    return new org.springframework.security.core.userdetails.User(String.join("-", email, email), user.getPassword(),
      true, true, true, true, grantedAuthorities);
  }
}
