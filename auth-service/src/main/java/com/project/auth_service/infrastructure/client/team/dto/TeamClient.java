package com.project.auth_service.infrastructure.client.team.dto;


import com.project.auth_service.domain.model.UserRole;
import com.project.auth_service.presentation.response.ApiSuccessResponse;
import com.project.auth_service.presentation.response.UserResponse;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "team-service",
        url = "http://3.37.198.42:19093",
        configuration = TeamFeignClientConfig.class,
        fallbackFactory = TeamFallback.class
)
public interface TeamClient {

//    @DeleteMapping("/api/v1/teams/byUser")
//    ApiSuccessResponse<?> deleteTeamsByUserId(
//            @PathVariable("userId") String userId,
//            @RequestHeader(name = "X-Role") UserRole userRole
//    );
}
