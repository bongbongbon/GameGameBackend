package com.project.quiz_service.repository;

import com.project.quiz_service.domain.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Page<Quiz> findAll(Pageable pageable);

    Page<Quiz> findByUsernameContaining(String username, Pageable pageable);

    @Query("SELECT q FROM Quiz q ORDER BY q.createdAt DESC")
    Page<Quiz> findAllQuizzes(Pageable pageable);
}
