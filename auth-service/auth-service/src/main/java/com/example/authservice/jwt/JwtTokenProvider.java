package com.example.authservice.jwt;

import com.example.authservice.constants.JWTConstants;
import com.example.authservice.model.response.dto.NetworkResponseDTO;
import com.example.authservice.utils.NetworkUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

  @Autowired
  JwtProperties jwtProperties;

  private String secretKey;

  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(jwtProperties.getSecretKey().getBytes());
  }

  public String createToken(String email, HttpServletRequest request) {
    NetworkResponseDTO network = NetworkUtils.getDeviceAddresses.apply(request);

    return Jwts.builder().setSubject(email).claim("mac", network.getMacAddress()).claim("ip", network.getIpAddress())
      .setIssuer(JWTConstants.JWT_KEY).setExpiration(calculateExpirationDate())
      .signWith(SignatureAlgorithm.HS256, secretKey).compact();

  }

  private Date calculateExpirationDate() {
    Date now = new Date();
    return new Date(now.getTime() + jwtProperties.getValidityInMilliseconds());
  }

}
