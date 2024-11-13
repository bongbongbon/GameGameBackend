package com.project.auth_service.presentation.response;

import com.project.auth_service.domain.model.Auth;

public record AuthResponse(String accessToken) {

    public static AuthResponse of(String accessToken) {
        return new AuthResponse(accessToken);
    }
}
