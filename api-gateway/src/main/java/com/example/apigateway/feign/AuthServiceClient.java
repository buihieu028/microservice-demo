package com.example.apigateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import com.example.commonservice.model.*;
import org.springframework.web.bind.annotation.*;
import com.example.authservice.model.LoginRequest;

@Service
@FeignClient(value = "auth-service")
public interface AuthServiceClient {
  @GetMapping("/user/get-infor")
  User getUserByEmail(@RequestParam("email") String email);

  @PostMapping("/login")
  ResponseData login(@RequestBody LoginRequest loginRequest);
}
