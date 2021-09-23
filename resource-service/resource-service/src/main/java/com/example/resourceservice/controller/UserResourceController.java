package com.example.resourceservice.controller;

import com.example.resourceservice.dtos.UserDetailDTO;
import com.example.resourceservice.feignService.UserServiceFeign;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserResourceController {
  @Autowired
  UserServiceFeign userServiceFeign;

  public static final Logger LOGGER = LoggerFactory.getLogger(UserResourceController.class);

  @GetMapping("/list-user")
  //@CircuitBreaker(name = "resource-service", fallbackMethod = "otherList")
  @HystrixCommand(fallbackMethod = "otherList")
  public List<UserDetailDTO> getUserDetail() {
    LOGGER.info("call from feign client");
    return userServiceFeign.getListUserDetail();
  }

  @PostMapping("/create")
  public UserDetailDTO saveUser(@RequestBody UserDetailDTO userDetailDTO) {
    LOGGER.info("call save from feign client");
    return userServiceFeign.saveNewUser(userDetailDTO);
  }

  private List<UserDetailDTO> otherList(Throwable t) {
    List<UserDetailDTO> userDetailDTOList = new ArrayList<>();
    UserDetailDTO userDetailDTO1 = new UserDetailDTO();
    userDetailDTO1.setId("1");
    userDetailDTO1.setAge("20");
    userDetailDTO1.setName("Hihi");
    userDetailDTOList.add(userDetailDTO1);
    return userDetailDTOList;
  }

}
