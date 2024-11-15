package com.project.teamservice.infrastructure.client;

import com.project.teamservice.infrastructure.configuration.UserFeignClientConfig;
import com.project.teamservice.presentation.response.ApiSuccessResponse;
import com.project.teamservice.infrastructure.client.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(
        name = "auth-service",
        url = "http://3.37.198.42:19091",
        configuration = UserFeignClientConfig.class,
        fallbackFactory = UserFallback.class
)
public interface UserClient {

    @GetMapping("/api/v1/auth/{userId}")
    ApiSuccessResponse<UserResponse> getUser(@PathVariable(name = "userId") Long userId);

}
