package com.project.resumeservice.application.dto;

import java.util.List;

public record ResumeDto(String title,
                        List<String> selectedJobs,
                        List<CareerDto> careerDtos) {

    public static ResumeDto create(String title,
                                   List<String> selectedJobs,
                                   List<CareerDto> careerDtos) {
        return new ResumeDto(
                title,
                selectedJobs,
                careerDtos);
    }
}
