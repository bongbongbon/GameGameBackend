package com.project.auth_service.presentation.request;

import com.project.auth_service.application.dto.LoginDto;
import com.project.auth_service.application.dto.SignUpDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.extern.java.Log;

public record LoginRequest(
        @NotNull(message = "email cannot be null")
        @Size(min =2, message = "Email not be less than two characters")
        @Email
        String email,

        @NotNull(message = "Password cannot be null")
        @Size(min = 2, message = "Password not be less than two characters")
        String password) {

    public LoginDto toDto() {
        return LoginDto.create(
                this.email,
                this.password
        );
    }

}
