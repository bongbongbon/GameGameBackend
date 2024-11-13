package com.project.resumeservice.presentation.response;

import com.project.resumeservice.domain.model.Career;

import java.util.List;
import java.util.stream.Collectors;

public record CareerResponse(Long id,
                             Long userId,
                             String company,
                             String position,
                             String department,
                             String job,
                             String startDate,
                             String endDate,
                             String description) {

    public static CareerResponse of(Career career) {
        return new CareerResponse(
                career.getId(),
                career.getUserId(),
                career.getCompany(),
                career.getPosition(),
                career.getDepartment(),
                career.getJob(),
                career.getStartDate(),
                career.getEndDate(),
                career.getDescription()
        );
    }

    public static List<CareerResponse> of(List<Career> careers) {
        return careers.stream()
                .map(CareerResponse::of)
                .collect(Collectors.toList());
    }
}
