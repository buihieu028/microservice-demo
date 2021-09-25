package com.example.apigateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import com.example.commonservice.model.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(value = "auth-service")
@RequestMapping(value = "/user")
public interface AuthServiceClient {
  @GetMapping("/get-infor")
  User getUserByEmail(@PathVariable("email") String email);
}
