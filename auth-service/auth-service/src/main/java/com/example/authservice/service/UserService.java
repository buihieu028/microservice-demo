package com.example.authservice.service;

import com.example.authservice.model.response.UserDetailResponse;
import com.example.commonservice.model.User;

public interface UserService {
  UserDetailResponse getDetailUser(Long userId, String email) throws Exception;

  User getUserByIdAndEmail(Long userId, String email);
}
