package com.project.quiz_service.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultRequest {

    private Long quizId;

    private String username;

    private String userAnswer;

    private Boolean isCorrect;
}
