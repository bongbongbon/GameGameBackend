package com.project.teamservice.domain.repository;

import com.project.teamservice.domain.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>, TeamCustomRepository {
    Page<Team> findAll(Pageable pageable);
}
