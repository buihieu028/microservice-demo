package com.example.resourceservice.feignService;

import com.example.resourceservice.dtos.UserDetailDTO;
import com.example.resourceservice.feignService.impl.UserServiceFallback;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "user-service")
public interface UserServiceFeign {

  @GetMapping("/api/user/list")
  List<UserDetailDTO> getListUserDetail();

  @PostMapping("/api/user/create")
  UserDetailDTO saveNewUser(@RequestBody UserDetailDTO userDetailDTO);

}
