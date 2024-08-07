package com.project.quiz_service.service;

import com.project.quiz_service.client.UserClient;
import com.project.quiz_service.domain.Quiz;
import com.project.quiz_service.exception.CustomException;
import com.project.quiz_service.repository.QuizRepository;
import com.project.quiz_service.request.QuizCheckAnswerRequest;
import com.project.quiz_service.request.QuizRequest;
import com.project.quiz_service.request.ResultRequest;
import com.project.quiz_service.response.QuizResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final UserClient userClient;
    private final ResultService resultService;
    private final RedisService redisService;

    // 퀴즈 만들기
    public void createQuiz(QuizRequest request, String token) {

        Quiz quiz = Quiz.builder()
                .category(request.getCategory())
                .username(userClient.getCurrentUser(token))
                .title(request.getTitle())
                .content(request.getContent())
                .answer(request.getAnswer())
                .build();

        quizRepository.save(quiz);
    }
    public QuizResponse updateQuiz(QuizRequest quizRequest, Long quizId) {

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> CustomException.QUIZ_NOT_FOUND);

        quiz.setCategory(quizRequest.getCategory());
        quiz.setTitle(quizRequest.getTitle());
        quiz.setAnswer(quizRequest.getAnswer());


        return QuizResponse.fromEntity(quiz);
    }

    // 퀴즈 삭제
    public void deleteQuiz(Long quizId) {
    }

    // 퀴즈 전체조회(페이징 처리)
    public Page<Quiz> getQuizzes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return quizRepository.findAll(pageable);
    }

    // 퀴즈 조회
    public QuizResponse getQuiz(Long quizId) {

        // 레디스 조회수 증가 로직
        incrementViewCount(quizId);

        // 레디스에서 가져온 조회수
        int redisGetView = getViewCount(quizId);

        return QuizResponse.fromEntity(quizRepository.findById(quizId)
                .orElseThrow(() -> CustomException.QUIZ_NOT_FOUND), redisGetView);
    }

    // 정답유무 확인
    public Boolean checkAnswer(QuizCheckAnswerRequest request, String token) {

        Boolean isCorrect = false;

        Quiz quiz = quizRepository.findById(request.getQuizId())
                .orElseThrow(() -> CustomException.QUIZ_NOT_FOUND);

        // 유저가 입력한 답과 퀴즈 답과 일치시 true 변경
        if(request.getUserAnswer().equals(quiz.getAnswer())) {
            isCorrect = true;
        }


        // ResultRequest로 Result에 저장할 값 담기
        ResultRequest resultRequest = ResultRequest.builder()
                .quizId(request.getQuizId())
                .username(userClient.getCurrentUser(token))
                .userAnswer(request.getUserAnswer())
                .isCorrect(isCorrect)
                .build();


        // ResultCreate하기
        resultService.createResult(resultRequest);

        return isCorrect;
    }

    // 조회수 증가
    public void incrementViewCount(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> CustomException.QUIZ_NOT_FOUND);

        String key = "quizId: " + quizId;
        String viewsStr = redisService.getData(key);

        // 첫 조회에 레디스 저장 로직
        if (viewsStr == null) {
            viewsStr = String.valueOf(quiz.getViews());
        }

        int views = Integer.parseInt(viewsStr) + 1;

        redisService.setData(key, String.valueOf(views));
    }

    // 조회수 가져오기
    public int getViewCount(Long quizId) {
        String key = "quizId: " + quizId;
        String count = redisService.getData(key);
        int view = Integer.parseInt(count);
        return view;
    }

    // 주기적으로 Redis와 DB 동기화
    @Scheduled(fixedRate = 60000) // 1분마다 실행
    public void syncRedisToDb() {
        Set<String> keys = redisService.getKeys("quizId: *");

        for (String key : keys) {
            Long quizId = Long.parseLong(key.split(": ")[1]);
            int views = getViewCount(quizId);

            Quiz quiz = quizRepository.findById(quizId)
                    .orElseThrow(() -> CustomException.QUIZ_NOT_FOUND);
            quiz.setViews(views);
            quizRepository.save(quiz);
        }
    }


}
