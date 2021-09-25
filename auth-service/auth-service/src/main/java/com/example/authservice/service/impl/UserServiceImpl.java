package com.example.authservice.service.impl;

import com.example.authservice.model.response.UserDetailResponse;
import com.example.authservice.repo.UserRepository;
import com.example.authservice.service.UserService;
import com.example.commonservice.model.User;
import com.example.commonservice.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetailResponse getDetailUser(Long userId, String email) throws Exception {
    LOGGER.info("START TO GET DETAIL USER");

    User user = userRepository.findByIdAndEmail(userId, email);
    if (user == null) {
      throw new BusinessException("User not found");
    }
    UserDetailResponse userDetailResponse = new UserDetailResponse();
    userDetailResponse.setId(user.getId());
    userDetailResponse.setEmail(user.getEmail());
    userDetailResponse.setFirstName(user.getFirstName());
    userDetailResponse.setLastName(user.getLastName());
    userDetailResponse.setAddress(user.getAddress());
    userDetailResponse.setPhoneNumber(user.getPhoneNumber());
    userDetailResponse.setGender(user.getGender());

    return userDetailResponse;

  }

  @Override
  public User getUserByIdAndEmail(Long userId, String email) {
    return userRepository.findByIdAndEmail(userId, email);
  }

  @Override
  public User getUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }
}
