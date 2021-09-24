package com.example.authservice.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "jwt")
@Configuration
public class JwtProperties {
  private String secretKey;

  private long validityInMilliseconds;

  public JwtProperties() {
  }

  public JwtProperties(String secretKey, long validityInMilliseconds) {
    this.secretKey = secretKey;
    this.validityInMilliseconds = validityInMilliseconds;
  }

  public String getSecretKey() {
    return secretKey;
  }

  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }

  public long getValidityInMilliseconds() {
    return validityInMilliseconds;
  }

  public void setValidityInMilliseconds(long validityInMilliseconds) {
    this.validityInMilliseconds = validityInMilliseconds;
  }
}
