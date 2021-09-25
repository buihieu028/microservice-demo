package com.example.apigateway.security.jwt;

import com.example.apigateway.exception.UnauthorisedException;
import com.example.apigateway.feign.AuthServiceClient;
import com.example.apigateway.security.CustomUserDetailService;
import com.example.commonservice.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

@Component
public class JwtTokenProvider {
  private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

  private final JwtProperties jwtProperties;

  private final CustomUserDetailService customUserDetailService;

  private String secretKey;

  public JwtTokenProvider(JwtProperties jwtProperties, CustomUserDetailService customUserDetailService) {
    this.jwtProperties = jwtProperties;
    this.customUserDetailService = customUserDetailService;
  }

  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(jwtProperties.getSecretKey().getBytes());
  }

  public Authentication getAuthentication(String token) {
    UserDetails userDetails = this.customUserDetailService.loadUserByUsername(getUsername(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", null);
  }

  private String getUsername(String token) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
  }

  public String resolveToken(HttpServletRequest req) {
    String bearerToken = req.getHeader("Authorization");
    return (!Objects.isNull(bearerToken) && bearerToken.startsWith("Bearer")) ?
      bearerToken.substring(7, bearerToken.length()) :
      null;
  }

  public boolean validateToken(String token) {
    try {
      Jws<Claims> claims = Jwts.parser().setSigningKey("mysecret").parseClaimsJws(token);
      return (!claims.getBody().getExpiration().before(new Date()));
    } catch (JwtException | IllegalArgumentException e) {
      LOGGER.error("Expired or invalid JWT token");
      throw new UnauthorisedException("Unmatched JWT Token", "Request not authorized");
    }
  }

}
