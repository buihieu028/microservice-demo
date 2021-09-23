package com.example.authservice.model.response;

public class LoginUserResponse {
  private String email;
  private String firstName;
  private String lastName;
  private String fullName;
  private String gender;
  private String address;
  private String phoneNumber;
  private int status;

  public LoginUserResponse() {
  }

  public LoginUserResponse(String email, String firstName, String lastName, String fullName, String gender,
    String address, String phoneNumber, int status) {
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.fullName = fullName;
    this.gender = gender;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.status = status;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }
}
