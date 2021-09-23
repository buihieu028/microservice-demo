package com.example.userservice.controller;

import com.example.userservice.dto.UserDetailDTO;
import com.example.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

  public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
  @Autowired
  private UserService userService;

  @GetMapping(value = "/list")
  public List<UserDetailDTO> getDetailDTOList() {
    LOGGER.info("call list from User service");
    return userService.userDetailDTOList();
  }

  @PostMapping(value = "/create")
  public UserDetailDTO saveNewUser(@RequestBody UserDetailDTO userDetailDTO) {
    LOGGER.info("call save new user from User service");
    return userService.saveNewUser(userDetailDTO);
  }
}
