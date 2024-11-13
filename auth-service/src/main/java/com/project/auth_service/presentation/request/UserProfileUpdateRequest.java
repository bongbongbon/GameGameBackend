package com.project.auth_service.presentation.request;

import com.project.auth_service.application.dto.UserDto;
import com.project.auth_service.application.dto.UserProfileUpdateDto;

public record UserProfileUpdateRequest(String email,
                                       String nickName,
                                       String phoneNumber) {

    public UserProfileUpdateDto toDto() {
        return UserProfileUpdateDto.create(
                this.email,
                this.nickName,
                this.phoneNumber
        );
    }
}
