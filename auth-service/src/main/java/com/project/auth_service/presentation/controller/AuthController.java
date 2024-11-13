package com.project.auth_service.presentation.controller;

import com.project.auth_service.application.dto.UserDto;
import com.project.auth_service.application.service.AuthService;
import com.project.auth_service.domain.model.UserRole;
import com.project.auth_service.presentation.request.LoginRequest;
import com.project.auth_service.presentation.request.SignUpRequest;
import com.project.auth_service.presentation.request.UserProfileUpdateRequest;
import com.project.auth_service.presentation.response.ApiSuccessResponse;
import com.project.auth_service.presentation.response.AuthResponse;
import com.project.auth_service.presentation.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 회원 가입
    @PostMapping("/signUp")
    public ApiSuccessResponse<?> signUp(@RequestBody @Valid SignUpRequest request) {

        authService.signUp(request.toDto());

        return ApiSuccessResponse.NO_DATA_RESPONSE;
    }

    // 로그인
    @PostMapping("/login")
    public ApiSuccessResponse<AuthResponse> login(@RequestBody @Valid LoginRequest request) {

        return ApiSuccessResponse.from(authService.login(request.toDto()));
    }

    @GetMapping
    public ApiSuccessResponse<UserResponse> getUser(@RequestHeader(name = "X-UserId") Long userId,
                                                    @RequestHeader(name = "X-Role") UserRole userRole) {

        return ApiSuccessResponse.from(authService.getUser(userId, userRole));
    }

    @GetMapping("/{userId}")
    public ApiSuccessResponse<UserResponse> getUser(@PathVariable(name = "userId") Long userId) {

        return ApiSuccessResponse.from(authService.getUser(userId));
    }

    @PatchMapping("/profile")
    public ApiSuccessResponse<?> updateUserProfile(@RequestHeader(name = "X-UserId") Long userId,
                                            @RequestHeader(name = "X-Role") UserRole userRole,
                                            @RequestBody UserProfileUpdateRequest request) {

        return ApiSuccessResponse.from(authService.updateUserProfile(userId, userRole, request.toDto()));
    }

    @DeleteMapping
    public ApiSuccessResponse<?> deleteUser(@RequestHeader(name = "X-UserId") Long userId,
                                            @RequestHeader(name = "X-Role") UserRole userRole,
                                            @RequestParam(name = "emailCheck") String emailCheck) {

        authService.deleteUser(userId, userRole, emailCheck);

        return ApiSuccessResponse.NO_DATA_RESPONSE;
    }


}
