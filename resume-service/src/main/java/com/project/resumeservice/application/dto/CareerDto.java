package com.project.resumeservice.application.dto;

import com.project.resumeservice.presentation.request.CareerCreateRequest;

import java.time.LocalDate;
import java.util.List;

public record CareerDto(String company,
                        String position,
                        String department,
                        String job,
                        String startDate,
                        String endDate,
                        String description) {

    public static CareerDto create(String company,
                                   String position,
                                   String department,
                                   String job,
                                   String startDate,
                                   String endDate,
                                   String description) {
        return new CareerDto(
                company,
                position,
                department,
                job,
                startDate,
                endDate,
                description);
    }
}
