package com.project.user_service.service;

import com.project.auth_service.model.UserCreatedEvent;
import com.project.user_service.model.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCreatedEventListener {

    private final UserProfileService userProfileService;

    // user에서 create 이벤트 발생시 만들때 userprofile로 저장
    @KafkaListener(topics = "user_created", groupId = "user-group")
    public void handleUserCreated(UserCreatedEvent event) {


            UserProfile userProfile =  UserProfile.builder()
                    .email(event.getEmail())
                    .nickname(event.getNickname())
                    .gender(event.getGender())
                    .build();


            userProfileService.createUserProfile(userProfile);

    }
}
