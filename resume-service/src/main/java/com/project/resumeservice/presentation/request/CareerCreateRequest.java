package com.project.resumeservice.presentation.request;

import com.project.resumeservice.application.dto.CareerDto;

public record CareerCreateRequest(String company,
                                  String position,
                                  String department,
                                  String job,
                                  String startDate,
                                  String endDate,
                                  String description) {

    public CareerDto toDto() {
        return CareerDto.create(
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
