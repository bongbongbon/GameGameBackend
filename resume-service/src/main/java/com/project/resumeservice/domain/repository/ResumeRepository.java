package com.project.resumeservice.domain.repository;

import com.project.resumeservice.domain.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long>, ResumeCustomRepository {
    long countByUserId(Long userId);
    Optional<List<Resume>> findByUserId(Long userId);
}
