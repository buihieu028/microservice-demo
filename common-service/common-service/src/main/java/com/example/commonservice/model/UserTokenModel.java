package com.example.commonservice.model;

public class UserTokenModel {
  private long userId;

  private String email;

  public UserTokenModel() {
  }

  public UserTokenModel(long userId, String email) {
    this.userId = userId;
    this.email = email;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
