package com.example.resourceservice.service;

import com.example.resourceservice.dto.UserDetailDTO;

import java.util.List;

public interface UserService {
  List<UserDetailDTO> userDetailDTOList();

  UserDetailDTO saveNewUser(UserDetailDTO userDetailDTO);
}
