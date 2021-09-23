package com.example.authservice.service;

import com.example.authservice.model.LoginRequest;
import com.example.authservice.model.UserRequest;
import com.example.authservice.model.response.LoginUserResponse;
import com.example.commonservice.model.User;

public interface UserService {

  LoginUserResponse login(LoginRequest loginRequest) throws Exception;

  User registerNewEmail(UserRequest userRequest) throws Exception;
}
