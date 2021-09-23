package com.example.userservice.feignService.impl;

import com.example.userservice.dtos.UserDetailDTO;
import com.example.userservice.feignService.UserServiceFeign;

import java.util.ArrayList;
import java.util.List;

public class UserServiceFallback implements UserServiceFeign {
  @Override
  public List<UserDetailDTO> getListUserDetail() {
    List<UserDetailDTO> userDetailDTOList = new ArrayList<>();
    UserDetailDTO userDetailDTO1 = new UserDetailDTO();
    userDetailDTO1.setId("1");
    userDetailDTO1.setAge("20");
    userDetailDTO1.setName("Hihi");
    userDetailDTOList.add(userDetailDTO1);
    return userDetailDTOList;
  }

  @Override
  public UserDetailDTO saveNewUser(UserDetailDTO userDetailDTO) {
    return null;
  }
}
