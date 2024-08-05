package com.project.quiz_service.repository;

import com.project.quiz_service.domain.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    Page<Result> findByUsernameOrderByCreatedAtDesc(String username, Pageable pageable);

}
