package com.example.authservice.controller;

import com.example.authservice.model.response.UserDetailResponse;
import com.example.authservice.service.AuthenticateService;
import com.example.authservice.service.UserService;
import com.example.commonservice.model.ResponseData;
import com.example.commonservice.exception.*;
import com.example.commonservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private AuthenticateService authenticateService;

  @GetMapping("detail-infor")
  public ResponseData getUserDetailiInformation(@RequestParam(name = "id") Long id,
    @RequestParam(name = "email") String email) {

    try {
      UserDetailResponse userDetailResponse = userService.getDetailUser(id, email);
      return new ResponseData("SUCCESS", userDetailResponse);
    } catch (BusinessException e) {
      return new ResponseData("ERROR", e.getMessage());
    } catch (Exception ex) {
      return new ResponseData("ERROR", "SYSTEM_ERROR");
    }

  }

  @GetMapping("/get-infor")
  public User getUserInfor(@RequestParam(name = "email") String email) {
    return authenticateService.loadByEmail(email);
  }
}
