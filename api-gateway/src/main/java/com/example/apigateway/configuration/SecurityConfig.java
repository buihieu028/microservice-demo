package com.example.apigateway.configuration;

import com.example.apigateway.security.jwt.JwtConfigurer;
import com.example.apigateway.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  JwtTokenProvider jwtTokenProvider;

  @Override
  public void configure(HttpSecurity httpSecurity) throws Exception {

    httpSecurity.httpBasic().disable().csrf().disable().sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().antMatchers("/auth/login")
      .permitAll().anyRequest().authenticated();
    httpSecurity.apply(new JwtConfigurer(jwtTokenProvider));
    //      .and()
    //      .apply(new JwtConfigurer(jwtTokenProvider));

  }

  @Bean
  public AuthenticationEntryPoint unauthorizedEntryPoint() {
    return ((request, response, authException) -> response
      .sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"));
  }
}
