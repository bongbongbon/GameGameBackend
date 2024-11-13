package com.project.resumeservice.presentation.request;

import com.project.resumeservice.application.dto.ResumeDto;
import com.project.resumeservice.application.dto.ResumeUpdateDto;

import java.util.List;
import java.util.stream.Collectors;

public record ResumeUpdateRequest(String title,
                                  List<String> selectedJobs,
                                  List<CareerUpdateRequest> careerUpdateRequests) {

public ResumeUpdateDto toDto() {
    return ResumeUpdateDto.create(
            this.title,
            this.selectedJobs,
            this.careerUpdateRequests.stream()
                    .map(CareerUpdateRequest::toDto)
                    .collect(Collectors.toList())
    );
}
}
