package com.project.auth_service.infrastructure.client.team.dto;

import com.project.auth_service.presentation.response.ApiSuccessResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TeamFallback implements TeamClient{

    private final Throwable cause;

    public TeamFallback(Throwable cause) {
        this.cause = cause;
    }

}
