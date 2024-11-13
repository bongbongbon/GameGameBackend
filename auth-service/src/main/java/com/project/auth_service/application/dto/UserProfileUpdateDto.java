package com.project.auth_service.application.dto;

public record UserProfileUpdateDto(String email,
                           String nickName,
                           String phoneNumber) {

    public static UserProfileUpdateDto create(
            String email,
            String nickName,
            String phoneNumber) {

        return new UserProfileUpdateDto(
                email,
                nickName,
                phoneNumber);
    }

}
