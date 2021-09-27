package com.example.apigateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import com.example.commonservice.model.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "auth-service")
public interface AuthServiceClient {
  @GetMapping("/user/get-infor")
  User getUserByEmail(@RequestParam("email") String email);
}
