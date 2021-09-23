package com.example.authservice.controller;

import com.example.authservice.model.LoginRequest;
import com.example.authservice.model.UserRequest;
import com.example.authservice.service.UserService;
import com.example.commonservice.exception.BusinessException;
import com.example.commonservice.model.ResponseData;
import com.example.commonservice.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class AuthController {
  private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

  @Autowired
  private UserService userService;

  @PostMapping("/create-account")
  public ResponseData createNewAccount(@Valid @RequestBody UserRequest userRequest, BindingResult bindingResult) {

    try {
      User newUser = userService.registerNewEmail(userRequest);
      if (newUser == null) {
        return new ResponseData("ERROR", "DATA_INVALID");
      }
      return new ResponseData("SUCCESS", newUser);
    } catch (BusinessException e) {
      return new ResponseData("ERROR", e.getMessage());
    } catch (Exception ex) {
      return new ResponseData("ERROR", "SYSTEM_ERROR");
    }
  }

  @PostMapping("/login")
  public ResponseData login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
    try {
      User loggedUser = userService.login(loginRequest);
      return new ResponseData("SUCCESS_LOGIN", loggedUser);
    } catch (BusinessException e) {
      return new ResponseData("ERROR", e.getMessage());
    } catch (Exception ex) {
      return new ResponseData("ERROR", "SYSTEM_ERROR");
    }
  }
}
