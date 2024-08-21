package com.project.quiz_service.service;

import com.project.quiz_service.response.QuizResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class RankService {

    private final ZSetOperations<String, QuizResponse> rankOps;

    public RankService(RedisTemplate<String, QuizResponse> rankTemplate) {
        this.rankOps = rankTemplate.opsForZSet();
    }

    // ZSet에서 quiz의 점수를 증가시키는 메서드
    public void incrementQuizScore(String key, QuizResponse quizResponse, double increment) {
        rankOps.incrementScore(key, quizResponse, increment);
    }

    // ZSet에서 상위 n개의 quiz를 가져오는 메서드 (점수가 높은 순으로)
    public Set<QuizResponse> getTopQuizzes(String key, long count) {
        return rankOps.reverseRange(key, 0, count - 1);
    }


    // 매일 자정에 랭킹 데이터를 초기화하는 메서드
    @Scheduled(cron = "0 0 0 * * *")  // 매일 자정 (00:00:00)에 실행
    public void resetDailyRanking() {
        rankOps.getOperations().delete("quizAnswerRanks");  // key 이름은 필요에 따라 설정
    }

}
