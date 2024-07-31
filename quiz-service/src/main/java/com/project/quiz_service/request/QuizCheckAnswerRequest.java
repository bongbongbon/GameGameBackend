package com.project.quiz_service.request;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizCheckAnswerRequest {

    private Long quizId;

    private String userAnswer;
}
