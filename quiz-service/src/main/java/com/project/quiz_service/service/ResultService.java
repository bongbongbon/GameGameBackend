package com.project.quiz_service.service;

import com.project.quiz_service.client.UserClient;
import com.project.quiz_service.domain.Quiz;
import com.project.quiz_service.domain.Result;
import com.project.quiz_service.exception.CustomException;
import com.project.quiz_service.repository.QuizRepository;
import com.project.quiz_service.response.ResultResponse;
import com.project.quiz_service.repository.ResultRepository;
import com.project.quiz_service.request.ResultRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultService {

    private final ResultRepository resultRepository;
    private final UserClient userClient;
    private final QuizRepository quizRepository;

    // 퀴즈 결과 만들기
    public void createResult(ResultRequest request) {

        //request로 받은 퀴즈 아이디로 퀴즈 찾기
        Quiz quiz = quizRepository.findById(request.getQuizId())
                .orElseThrow(() -> CustomException.QUIZ_NOT_FOUND);

        Result result = Result.builder()
                .quiz(quiz)
                .username(request.getUsername())
                .isCorrect(request.getIsCorrect())
                .build();

        resultRepository.save(result);
    }

    public Page<ResultResponse> getMyResultList(String token, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        String loginUser = userClient.getCurrentUser(token);

        Page<Result> resultPage = resultRepository.findByUsernameOrderByCreatedAtDesc(loginUser, pageable);


        return resultPage.map(ResultResponse::fromEntity);
    }




}
