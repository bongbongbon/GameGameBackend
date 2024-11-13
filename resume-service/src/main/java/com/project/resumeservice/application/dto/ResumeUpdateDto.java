package com.project.resumeservice.application.dto;

import java.util.List;

public record ResumeUpdateDto(
                              String title,
                              List<String> selectedJobs,
                              List<CareerUpdateDto> careerUpdateDtos) {

    public static ResumeUpdateDto create(String title,
                                         List<String> selectedJobs,
                                         List<CareerUpdateDto> careerUpdateDtos) {
        return new ResumeUpdateDto(
                title,
                selectedJobs,
                careerUpdateDtos);
    }
}