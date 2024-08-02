package com.project.quiz_service.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "AUTH-SERVICE")
public interface UserClient {

    @PostMapping("/api/auth/me")
    String getCurrentUser(@RequestHeader(name = "Authorization") String token);
}
