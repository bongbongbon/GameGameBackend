package com.project.quiz_service.service;

import com.project.quiz_service.client.UserClient;
import com.project.quiz_service.domain.Quiz;
import com.project.quiz_service.exception.CustomException;
import com.project.quiz_service.repository.QuizRepository;
import com.project.quiz_service.request.QuizCheckAnswerRequest;
import com.project.quiz_service.request.QuizCreateRequest;
import com.project.quiz_service.response.QuizResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final UserClient userClient;

    // 퀴즈 만들기
    public void createQuiz(QuizCreateRequest request, String token) {

        Quiz quiz = Quiz.builder()
                .category(request.getCategory())
                .username(userClient.getCurrentUser(token))
                .title(request.getTitle())
                .content(request.getContent())
                .answer(request.getAnswer())
                .build();

        quizRepository.save(quiz);
    }

    // 퀴즈 전체조회(페이징 처리)
    public Page<Quiz> getQuizzes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return quizRepository.findAll(pageable);
    }

    // 퀴즈 조회
    public QuizResponse getQuiz(Long quizId) {

        return QuizResponse.fromEntity(quizRepository.findById(quizId)
                .orElseThrow(() -> CustomException.QUIZ_NOT_FOUND));
    }

    // 정답유무 확인
    public Boolean checkAnswer(QuizCheckAnswerRequest request) {

        Boolean isCorrect = false;

        Quiz quiz = quizRepository.findById(request.getQuizId())
                .orElseThrow(() -> CustomException.QUIZ_NOT_FOUND);

        // 유저가 입력한 답과 퀴즈 답과 일치시 true 변경
        if(request.getUserAnswer().equals(quiz.getAnswer())) {
            isCorrect = true;
        }

        return isCorrect;
    }
}
