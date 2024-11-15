package com.project.teamservice.infrastructure.client.dto;

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
