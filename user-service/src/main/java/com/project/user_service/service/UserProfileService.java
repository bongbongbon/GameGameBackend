package com.project.user_service.service;


import com.project.user_service.model.UserProfile;
import com.project.user_service.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    // userProfile 저장
    public void createUserProfile(UserProfile userProfile) {

        userProfileRepository.save(userProfile);
    }
}
