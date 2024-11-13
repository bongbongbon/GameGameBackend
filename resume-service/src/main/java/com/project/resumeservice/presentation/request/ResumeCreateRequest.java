package com.project.resumeservice.presentation.request;

import com.project.resumeservice.application.dto.ResumeDto;

import java.util.List;
import java.util.stream.Collectors;

public record ResumeCreateRequest(String title,
                                  List<String> selectedJobs,
                                  List<CareerCreateRequest> careerCreateRequests) {

    public ResumeDto toDto() {
        return ResumeDto.create(
                this.title,
                this.selectedJobs,
                this.careerCreateRequests.stream()
                        .map(CareerCreateRequest::toDto)
                        .collect(Collectors.toList())
        );
    }
}
