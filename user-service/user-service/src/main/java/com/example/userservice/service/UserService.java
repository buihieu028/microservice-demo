package com.example.userservice.service;

import com.example.userservice.dto.UserDetailDTO;

import java.util.List;

public interface UserService {
  List<UserDetailDTO> userDetailDTOList();

  UserDetailDTO saveNewUser(UserDetailDTO userDetailDTO);

  UserDetailDTO findById(Long id);
}
