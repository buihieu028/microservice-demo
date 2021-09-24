package com.example.apigateway.jwt;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
  private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

  @Override
  public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
    AuthenticationException e) throws IOException, ServletException {
    log.debug("Jwt authentication failed" + e);

    httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Jwt authentication failed");
  }
}