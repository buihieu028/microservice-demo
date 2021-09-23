package com.example.authservice.service.impl;

import com.example.authservice.model.LoginRequest;
import com.example.authservice.model.UserRequest;
import com.example.authservice.model.response.LoginUserResponse;
import com.example.authservice.repo.UserRepository;
import com.example.authservice.service.UserService;
import com.example.commonservice.exception.BusinessException;
import com.example.commonservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserRepository userRepository;

  @Override
  public LoginUserResponse login(LoginRequest request) throws Exception {
    User foundedUser = userRepository.findByEmail(request.getEmail());
    if (passwordEncoder.matches(foundedUser.getPassword(), request.getPassword())) {
      LoginUserResponse loginUserResponse = new LoginUserResponse();
      loginUserResponse.setEmail(foundedUser.getEmail());
      loginUserResponse.setFirstName(foundedUser.getFirstName());
      loginUserResponse.setLastName(foundedUser.getLastName());
      loginUserResponse.setAddress(foundedUser.getAddress());
    }
  }

  @Override
  public User registerNewEmail(UserRequest userRequest) throws Exception {
    User existUser = userRepository.findByEmail(userRequest.getEmail());
    if (existUser != null) {
      throw new BusinessException("Email existed");
    }

    //new user
    User user = new User();
    user.setEmail(userRequest.getEmail());
    List<User> userList = userRepository.findAll();
    user.setId((long) (userList.size() + 1));
    user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
    if (!userRequest.getConfirmPassword().equals(userRequest.getPassword())) {
      throw new BusinessException("Password and Confirm password not match!!");
    }
    user.setFirstName(userRequest.getFirstName());
    user.setLastName(userRequest.getLastName());
    user.setFullName(userRequest.getFirstName() + " " + userRequest.getLastName());
    user.setGender(userRequest.getGender());
    user.setAddress(userRequest.getAddress());
    user.setPhoneNumber(userRequest.getPhoneNumber());
    user.setStatus(1);
    long time = System.currentTimeMillis();
    user.setCreatedAt(time);
    user.setUpdatedAt(time);
    User createdUser = userRepository.save(user);
    if (createdUser == null) {
      throw new BusinessException("Create User Fail");
    }
    return user;

  }
}
