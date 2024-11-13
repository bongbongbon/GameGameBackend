package com.project.resumeservice.presentation.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.resumeservice.domain.model.Resume;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record ResumeResponse(Long id,
                             String title,
                             Long userId
                            , List<String> selectedJobs,
                             List<CareerResponse> careerResponses,
                             @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
                             LocalDateTime createdAt) {

    public static ResumeResponse of(Resume resume) {
        return new ResumeResponse(
                resume.getId(),
                resume.getTitle(),
                resume.getUserId(),
                resume.getSelectedJobs(),
                CareerResponse.of(resume.getCareers()),
                resume.getCreatedAt()
        );
    }

    public static List<ResumeResponse> of(List<Resume> resumes) {
        return resumes.stream()
                .map(ResumeResponse::of)
                .collect(Collectors.toList());
    }

}
