package com.project.auth_service.presentation.response;

import com.project.auth_service.domain.model.Auth;

public record UserResponse(
        String email,
        String nickName,
        String phoneNumber
) {

    public static UserResponse of(Auth auth) {
        return new UserResponse(
                auth.getEmail(),
                auth.getNickName(),
                auth.getPhoneNumber()
        );
    }

}
