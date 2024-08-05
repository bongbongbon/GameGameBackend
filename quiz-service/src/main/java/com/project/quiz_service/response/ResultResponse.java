package com.project.quiz_service.response;

import com.project.quiz_service.domain.Quiz;
import com.project.quiz_service.domain.Result;
import com.project.quiz_service.repository.ResultRepository;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultResponse {

    private Long id;
    private QuizResponse quizResponse;
    private String userAnswer;
    private String username;
    private Boolean isCorrect;
    private LocalDateTime createdAt;




    public static ResultResponse fromEntity(Result result) {

        return ResultResponse.builder()
                .quizResponse(QuizResponse.fromEntity(result.getQuiz()))
                .userAnswer(result.getUserAnswer())
                .username(result.getUsername())
                .isCorrect(result.getIsCorrect())
                .build();
    }

    public static List<ResultResponse> fromEntityList(List<Result> results) {
        return results.stream()
                .map(ResultResponse::fromEntity)
                .collect(Collectors.toList());
    }



}
