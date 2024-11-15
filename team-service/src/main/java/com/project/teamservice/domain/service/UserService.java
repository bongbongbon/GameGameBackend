package com.project.teamservice.domain.service;

import com.project.teamservice.infrastructure.client.UserClient;
import com.project.teamservice.infrastructure.client.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient userClient;

    public UserResponse getUser(Long userId) {
        return userClient.getUser(userId).getData();
    }
}
