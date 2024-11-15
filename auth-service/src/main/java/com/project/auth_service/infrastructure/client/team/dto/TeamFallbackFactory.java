package com.project.auth_service.infrastructure.client.team.dto;

import com.project.auth_service.presentation.response.ApiSuccessResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TeamFallbackFactory implements FallbackFactory<TeamFallback> {


    @Override
    public TeamFallback create(Throwable cause) {
        log.info(cause.toString());
        return new TeamFallback(cause);
    }

}
