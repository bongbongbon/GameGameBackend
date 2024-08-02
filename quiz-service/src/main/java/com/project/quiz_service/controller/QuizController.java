package com.project.quiz_service.controller;

import com.project.quiz_service.request.QuizCheckAnswerRequest;
import com.project.quiz_service.request.QuizCreateRequest;
import com.project.quiz_service.response.ApiSuccessResponse;
import com.project.quiz_service.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/create")
    public ApiSuccessResponse<?> createQuiz(@RequestBody QuizCreateRequest request,
                                            @RequestHeader("Authorization") String token) {
        quizService.createQuiz(request, token);
        return ApiSuccessResponse.NO_DATA_RESPONSE;
    }

    // page처리로 퀴즈 전부 가져오기
    @GetMapping("/getAll")
    public ApiSuccessResponse<?> getAllQuizzes(@RequestParam(name = "page") int page,
                                               @RequestParam(name = "size") int size) {
        return ApiSuccessResponse.from(quizService.getQuizzes(page, size));
    }

    @GetMapping("/get/{id}")
    public ApiSuccessResponse<?> getQuiz(@PathVariable(name = "id") Long quizId) {
        return ApiSuccessResponse.from(quizService.getQuiz(quizId));
    }


    @PostMapping("/checkAnswer")
    public ApiSuccessResponse<?> checkAnswer(@RequestBody QuizCheckAnswerRequest request,
                                             @RequestHeader(name = "Authorization") String token) {

        return ApiSuccessResponse.from(quizService.checkAnswer(request, token));
    }


}
