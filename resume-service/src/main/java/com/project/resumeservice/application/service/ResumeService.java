package com.project.resumeservice.application.service;

import com.project.resumeservice.application.dto.ResumeDto;
import com.project.resumeservice.application.dto.ResumeUpdateDto;
import com.project.resumeservice.domain.model.Career;
import com.project.resumeservice.domain.model.Resume;
import com.project.resumeservice.domain.model.UserRole;
import com.project.resumeservice.domain.repository.CareerRepository;
import com.project.resumeservice.domain.repository.ResumeRepository;
import com.project.resumeservice.domain.validator.ResumeValidator;
import com.project.resumeservice.presentation.response.ResumeResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;

    private final CareerService careerService;

    private final ResumeValidator resumeValidator;

    private final CareerRepository  careerRepository;

    @Transactional
    public ResumeResponse createResume(Long userId, UserRole userRole, ResumeDto request) {

        // 하나의 이력서만 작성가능
        resumeValidator.validateSingleResumePerUser(userId);

        // 주문 상품 create
        List<Career> careers =
                careerService.createCareer(userId, userRole, request.careerDtos());

        Resume resume = Resume.create(
                userId,
                request.title(),
                request.selectedJobs(),
                careers
        );

        resumeRepository.save(resume);

        return ResumeResponse.of(resume);

    }

    @Transactional
    public ResumeResponse getResume(Long resumeId) {

        return ResumeResponse.of(resumeRepository.findById(resumeId).
                orElseThrow(() -> new RuntimeException("이력서 없음")));
    }

    @Transactional
    public List<ResumeResponse> getMyResume(Long userId, UserRole userRole) {

        return ResumeResponse.of(resumeRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("이력서 없음")));
    }

    @Transactional
    public ResumeResponse updateResume(Long userId, UserRole userRole, Long resumeId, ResumeUpdateDto request) {

        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new RuntimeException("이력서가 없습니다."));


        // Process career updates and additions
        request.careerUpdateDtos().forEach(careerDto -> {
            if (careerDto.id() == null) {
                // New career entry, save it
                Career newCareer = Career.create(
                        userId,
                        careerDto.company(),
                        careerDto.position(),
                        careerDto.department(),
                        careerDto.job(),
                        careerDto.startDate(),
                        careerDto.endDate(),
                        careerDto.description()
                );
                newCareer.setResume(resume); // Link the new career to the resume
                careerRepository.save(newCareer); // Save new career entry
            } else {
                // Existing career entry, update it through the careerService
                careerService.updateCareer(userId, userRole, careerDto.id(), careerDto);
            }
        });

        return resumeRepository.findById(resumeId).map(updateResume -> {
            resume.update(
                    request.title(),
                    request.selectedJobs()
            );
            return ResumeResponse.of(resume);
        }).orElseThrow(() -> new RuntimeException("이력서가 없습니다."));


    }



}
