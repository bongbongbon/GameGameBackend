package com.project.resumeservice.application.dto;

public record CareerUpdateDto(Long id,
                              String company,
                             String position,
                             String department,
                             String job,
                             String startDate,
                             String endDate,
                             String description) {

    public static CareerUpdateDto create(Long id,
                                   String company,
                                   String position,
                                   String department,
                                   String job,
                                   String startDate,
                                   String endDate,
                                   String description) {
        return new CareerUpdateDto(
                id,
                company,
                position,
                department,
                job,
                startDate,
                endDate,
                description);
    }
}