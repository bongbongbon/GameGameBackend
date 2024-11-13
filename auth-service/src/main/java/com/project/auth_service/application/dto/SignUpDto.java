package com.project.auth_service.application.dto;

import com.project.auth_service.domain.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record SignUpDto(
        String email,
        String password,
        String passwordCheck,
        String nickName,
        String phoneNumber,
        UserRole userRole) {

    public static SignUpDto create(
                                    String email,
                                    String password,
                                    String passwordCheck,
                                    String nickName,
                                    String phoneNumber,
                                    UserRole userRole) {
        return new SignUpDto(
                email,
                password,
                passwordCheck,
                nickName,
                phoneNumber,
                userRole);
    }
}
