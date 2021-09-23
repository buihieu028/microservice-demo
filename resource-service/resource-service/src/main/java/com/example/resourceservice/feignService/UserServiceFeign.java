package com.example.userservice.feignService;

import com.example.userservice.dtos.UserDetailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "user-service")
public interface UserServiceFeign {

  @GetMapping("/api/user/list")
  List<UserDetailDTO> getListUserDetail();

  @PostMapping("/api/user/create")
  UserDetailDTO saveNewUser(@RequestBody UserDetailDTO userDetailDTO);

}
