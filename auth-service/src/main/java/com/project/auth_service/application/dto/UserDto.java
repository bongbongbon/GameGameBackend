package com.project.auth_service.application.dto;

import com.project.auth_service.domain.model.UserRole;

public record UserDto(
        String email,
        String nickName,
        String password,
        String phoneNumber) {

    public static UserDto create(
            String email,
            String nickName,
            String password,
            String phoneNumber) {
        return new UserDto(
                email,
                nickName,
                password,
                phoneNumber);
    }
}
