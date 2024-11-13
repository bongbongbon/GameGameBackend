package com.project.resumeservice.application.service;

import com.project.resumeservice.application.dto.CareerDto;
import com.project.resumeservice.application.dto.CareerUpdateDto;
import com.project.resumeservice.domain.model.Career;
import com.project.resumeservice.domain.model.UserRole;
import com.project.resumeservice.domain.repository.CareerRepository;
import com.project.resumeservice.presentation.response.CareerResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CareerService {

    private final CareerRepository careerRepository;
    // OrderProductDto List로 받아 여러건의 주문 상품을 저장
    @Transactional
    public List<Career> createCareer(Long userId, UserRole userRole, List<CareerDto> requestList) {

        // 각 OrderProductDto를 OrderProduct로 변환 후 저장
        List<Career> careers = requestList.stream()
                .map(request -> Career.create(
                        userId,
                        request.company(),
                        request.position(),
                        request.department(),
                        request.job(),
                        request.startDate(),
                        request.endDate(),
                        request.description()
                ))
                .collect(Collectors.toList());

        // 리스트로 저장
        return careerRepository.saveAll(careers);
    }


    @Transactional
    public Career updateCareer(Long userId, UserRole userRole, Long careerId, CareerUpdateDto request) {

        return careerRepository.findById(careerId).map(career -> {
            career.update(
                    userId,
                    request.company(),
                    request.position(),
                    request.department(),
                    request.job(),
                    request.startDate(),
                    request.endDate(),
                    request.description()
            );

            return career;
        }).orElseThrow(() -> new RuntimeException("경력이 없습니다."));
    }

}
