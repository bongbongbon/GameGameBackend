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

    private int views;

    public static QuizResponse fromEntity(Quiz quiz) {
        return QuizResponse.builder()
                .id(quiz.getId())
                .category(quiz.getCategory())
                .title(quiz.getTitle())
                .content(quiz.getContent())
                .userAnswer(quiz.getAnswer())
                .views(quiz.getViews())
                .build();
    }

    // 레디스로 조회수 조회
    public static QuizResponse fromEntity(Quiz quiz, int redisGetView) {
        return QuizResponse.builder()
                .id(quiz.getId())
                .category(quiz.getCategory())
                .title(quiz.getTitle())
                .content(quiz.getContent())
                .userAnswer(quiz.getAnswer())
                .views(redisGetView)
                .build();
    }

    public static List<QuizResponse> fromEntity(List<Quiz> quizList) {
        return quizList.stream()
                .map(QuizResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
