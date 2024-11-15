package com.project.teamservice.infrastructure.client;

import com.project.teamservice.presentation.response.ApiSuccessResponse;
import com.project.teamservice.infrastructure.client.dto.UserResponse;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserFallback implements UserClient{

    private final Throwable cause;

    public UserFallback(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public ApiSuccessResponse<UserResponse> getUser(Long userId) {
        if (cause instanceof FeignException.NotFound) {
            log.error("Not found error");
        }
        log.error("Failed to get user {}", userId);
        return ApiSuccessResponse.from(null);
    }
}
