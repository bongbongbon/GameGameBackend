package com.project.auth_service.dto;

import com.project.auth_service.model.User;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTokenResponse {

    private String email;
    private String token;
    private String nickname;

    // Entity -> DTO
    public static UserTokenResponse fromEntity(User user, String token) {
        return UserTokenResponse.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .token(token)
                .build();
    }
}
