package com.project.auth_service.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatedEvent {

    private String email;

    private String nickname;

    private String gender;
}
