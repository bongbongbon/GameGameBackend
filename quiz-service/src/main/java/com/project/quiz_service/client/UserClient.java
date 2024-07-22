package com.project.quiz_service.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("USER-SERVICE")
public interface UserClient {

    @GetMapping("/api/users/me")
    String getCurrentUser(@RequestHeader("Authorization") String token);
}
