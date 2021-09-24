package com.example.authservice.service;

import com.example.authservice.model.LoginRequest;
import com.example.authservice.model.UserRequest;
import com.example.authservice.model.response.LoginUserResponse;
import com.example.commonservice.model.User;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticateService {

  String login(LoginRequest loginRequest, HttpServletRequest request) throws Exception;

  User registerNewEmail(UserRequest userRequest) throws Exception;

  User loadByEmail(String email);
}
