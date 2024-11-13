package com.project.resumeservice.presentation.request;

import com.project.resumeservice.application.dto.CareerDto;
import com.project.resumeservice.application.dto.CareerUpdateDto;

public record CareerUpdateRequest(Long id,
                                  String company,
                                  String position,
                                  String department,
                                  String job,
                                  String startDate,
                                  String endDate,
                                  String description){

public CareerUpdateDto toDto() {
    return CareerUpdateDto.create(
            id,
            company,
            position,
            department,
            job,
            startDate,
            endDate,
            description
        );
    }
}
