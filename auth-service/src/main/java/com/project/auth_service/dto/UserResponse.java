package com.project.auth_service.dto;


import com.project.auth_service.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;

    protected String email;

    protected String nickName;


    public static UserResponse fromEntity(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickName(user.getNickName())
                .build();
    }
}
