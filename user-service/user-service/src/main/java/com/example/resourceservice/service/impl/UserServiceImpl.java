package com.example.resourceservice.service.impl;

import com.example.resourceservice.dto.UserDetailDTO;
import com.example.resourceservice.model.UserDetails;
import com.example.resourceservice.repo.UserDetailRepository;
import com.example.resourceservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserDetailRepository userDetailRepository;

  @Override
  public List<UserDetailDTO> userDetailDTOList() {
    List<UserDetails> userDetailsList = userDetailRepository.findAll();
    if (CollectionUtils.isEmpty(userDetailsList)) {
      return null;
    }
    List<UserDetailDTO> userDetailDTOList = new ArrayList<>();
    for (UserDetails userDetails : userDetailsList) {
      UserDetailDTO userDetailDTO = new UserDetailDTO();
      userDetailDTO.setId(String.valueOf(userDetails.getId()));
      userDetailDTO.setAge(String.valueOf(userDetails.getAge()));
      userDetailDTO.setName(String.valueOf(userDetails.getName()));
      userDetailDTOList.add(userDetailDTO);
    }
    return userDetailDTOList;
  }

  @Override
  public UserDetailDTO saveNewUser(UserDetailDTO userDetailDTO) {
    UserDetails userDetails = new UserDetails();
    userDetails.setId(Integer.parseInt(userDetailDTO.getId()));
    userDetails.setAge(Integer.parseInt(userDetailDTO.getAge()));
    userDetails.setName(userDetailDTO.getName());

    UserDetails saveUserDetails = userDetailRepository.save(userDetails);
    userDetailDTO.setId(String.valueOf(saveUserDetails.getId()));
    userDetailDTO.setName(String.valueOf(saveUserDetails.getName()));
    userDetailDTO.setAge(String.valueOf(saveUserDetails.getAge()));
    return userDetailDTO;
  }

}
