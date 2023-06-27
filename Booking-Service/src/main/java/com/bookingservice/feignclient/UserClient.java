package com.bookingservice.feignclient;

import com.bookingservice.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "security-service", path = "auth-api")
public interface UserClient {
    @GetMapping("/findByUsername/{username}")
    UserResponse findUserByUsername(@PathVariable String username);
}
