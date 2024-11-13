package com.project.resumeservice.domain.repository;


import com.project.resumeservice.domain.model.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerRepository extends JpaRepository<Career, Long>, CareerCustomRepository {

}
