package com.project.quiz_service.request;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizCreateRequest {

    private String category;

    private String title;

    private String content;

    private String answer;
}
