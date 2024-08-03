package com.project.quiz_service.response;

import com.project.quiz_service.domain.Quiz;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizResponse {

    private Long id;

    private String category;

    private String title;

    private String content;

    private String userAnswer;

    public static QuizResponse fromEntity(Quiz quiz) {
        return QuizResponse.builder()
                .id(quiz.getId())
                .category(quiz.getCategory())
                .title(quiz.getTitle())
                .content(quiz.getContent())
                .userAnswer(quiz.getAnswer())
                .build();
    }

    public static List<QuizResponse> fromEntity(List<Quiz> quizList) {
        return quizList.stream()
                .map(QuizResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
