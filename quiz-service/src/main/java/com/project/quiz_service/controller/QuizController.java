package com.project.quiz_service.controller;

import com.project.quiz_service.domain.Quiz;
import com.project.quiz_service.exception.CustomException;
import com.project.quiz_service.repository.QuizRepository;
import com.project.quiz_service.request.QuizCheckAnswerRequest;
import com.project.quiz_service.request.QuizRequest;
import com.project.quiz_service.response.ApiSuccessResponse;
import com.project.quiz_service.service.QuizService;
import com.project.quiz_service.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService quizService;
    private final ResultService resultService;
    private final QuizRepository quizRepository;

    @PostMapping("/create")
    public ApiSuccessResponse<?> createQuiz(@RequestBody QuizRequest request,
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

    @PatchMapping("/update/{id}")
    public ApiSuccessResponse<?> updateQuiz(@PathVariable(name = "id") Long quizId,
                                            @RequestBody QuizRequest quizRequest) {

        return ApiSuccessResponse.from(quizService.updateQuiz(quizId, quizRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ApiSuccessResponse<?> deleteQuiz(@PathVariable(name = "id") Long quizId) {

        Quiz quiz = quizRepository.findById(quizId)
                        .orElseThrow(() -> CustomException.QUIZ_NOT_FOUND);

            quizRepository.delete(quiz); // 퀴즈 삭제 시 연관된 결과들도 자동 삭제됨

        return ApiSuccessResponse.NO_DATA_RESPONSE;
    }


    @PostMapping("/checkAnswer")
    public ApiSuccessResponse<?> checkAnswer(@RequestBody QuizCheckAnswerRequest request,
                                             @RequestHeader(name = "Authorization") String token) {

        return ApiSuccessResponse.from(quizService.checkAnswer(request, token));
    }


    @GetMapping("/myResult")
    public ApiSuccessResponse<?> getMyResultList(@RequestHeader(name = "Authorization")String token,
                                                 @RequestParam(name = "page") int page,
                                                 @RequestParam(name = "size") int size) {



        return ApiSuccessResponse.from(resultService.getMyResultList(token, page, size));
    }

    @GetMapping("/myQuizzes")
    public ApiSuccessResponse<?> getAllMyQuizzes(@RequestHeader(name = "Authorization")String token,
                                                 @RequestParam(name = "page") Integer page,
                                                 @RequestParam(name = "size") Integer size) {
        return ApiSuccessResponse.from(quizService.getAllMyQuizzes(token, page, size));
    }

}
