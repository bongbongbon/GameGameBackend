package com.project.auth_service.application.dto;

import com.project.auth_service.domain.model.UserRole;

public record LoginDto(String email,
                       String password) {

    public static LoginDto create(String email,
                                  String password) {

        return new LoginDto(
                email,
                password);
    }
}
