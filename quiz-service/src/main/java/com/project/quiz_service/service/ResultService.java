package com.project.quiz_service.service;

import com.project.quiz_service.domain.Result;
import com.project.quiz_service.repository.ResultRepository;
import com.project.quiz_service.request.ResultRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResultService {

    private final ResultRepository resultRepository;

    public void createResult(ResultRequest request) {
        Result result = Result.builder()
                .quizId(request.getQuizId())
                .username(request.getUsername())
                .isCorrect(request.getIsCorrect())
                .build();

        resultRepository.save(result);
    }
}
