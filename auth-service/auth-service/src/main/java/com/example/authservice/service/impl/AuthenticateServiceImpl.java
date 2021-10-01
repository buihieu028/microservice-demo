package com.example.authservice.service.impl;

import com.example.authservice.jwt.JwtTokenProvider;
import com.example.authservice.model.LoginRequest;
import com.example.authservice.model.UserRequest;
import com.example.authservice.repo.RoleRepository;
import com.example.authservice.repo.UserRepository;
import com.example.authservice.service.AuthenticateService;
import com.example.authservice.utils.DateUtils;
import com.example.commonservice.exception.*;
import com.example.commonservice.model.Role;
import com.example.commonservice.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {

  private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticateServiceImpl.class);
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Override
  public String login(LoginRequest loginRequest, HttpServletRequest request) throws Exception {
    LOGGER.info("LOGIN PROCESS STARTED");

    long startTime = DateUtils.getTimeInMillisecondsFromLocalDate();
    User user = userRepository.findByEmail(loginRequest.getEmail());
    if (user != null) {
      if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
        String jwtToken = jwtTokenProvider.createToken(loginRequest.getEmail(), request);
        LOGGER.info(
          "LOGIN PROCESS COMPLETED IN ::: " + (DateUtils.getTimeInMillisecondsFromLocalDate() - startTime + "ms"));
        return jwtToken;
      }
      throw new BusinessException("Incorrect password for this email");
    }
    throw new BusinessException("Email not existed");
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
    Role role = roleRepository.findByName("USER");
    user.setRole(role.getName());
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

  @Override
  public User loadByEmail(String email) {
    return userRepository.findByEmail(email);
  }
}
