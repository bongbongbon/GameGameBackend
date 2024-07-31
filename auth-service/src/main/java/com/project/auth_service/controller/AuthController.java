package com.project.auth_service.controller;


import com.project.auth_service.dto.LoginRequest;
import com.project.auth_service.dto.RegisterRequest;
import com.project.auth_service.dto.UserResponse;
import com.project.auth_service.dto.UserTokenResponse;
import com.project.auth_service.jwt.JwtUtil;
import com.project.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;
    private final JwtUtil jwtUtil;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request)
                                                    throws UnsupportedEncodingException {
        UserResponse successMember = authService.signUp(request);
        return ResponseEntity.status(HttpStatus.OK).body(successMember);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<UserTokenResponse> login(@RequestBody LoginRequest request) {
        UserTokenResponse userTokenResponse = authService.login(request);
        return ResponseEntity.status(HttpStatus.OK).body(userTokenResponse);
    }

    @PostMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String token) {

        return ResponseEntity.ok().body(jwtUtil.getEmailFromJwt(token));
    }




}
