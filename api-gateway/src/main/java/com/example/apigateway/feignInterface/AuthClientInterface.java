package com.example.apigateway.feignInterface;

import com.example.commonservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auth-service")
@Service
@RequestMapping(value = "/user")
public interface AuthClientInterface {
  @GetMapping("/get-infor")
  User findUserByEmail(@RequestParam(name = "email") String email);
}
