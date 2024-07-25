package com.project.auth_service.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String email;

    private String password;

    private String passwordCheck;

    private String nickName;

    private String gender;


}
