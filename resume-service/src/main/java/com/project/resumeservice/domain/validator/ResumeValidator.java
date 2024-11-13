package com.project.resumeservice.domain.validator;

import com.project.resumeservice.domain.model.Resume;
import com.project.resumeservice.domain.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResumeValidator {

    private final ResumeRepository resumeRepository;

    public void validateSingleResumePerUser(Long userId) {

        long resumeCount = resumeRepository.countByUserId(userId);

        if (resumeCount >= 3) {
            throw new RuntimeException("이력서는 최대 3개까지만 만들 수 있습니다.");
        }

    }
}
