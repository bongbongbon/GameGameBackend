package com.project.teamservice.presentation.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.teamservice.application.dto.TeamDto;
import com.project.teamservice.application.dto.TeamSearchDto;
import com.project.teamservice.domain.model.TeamCategory;

import java.time.LocalDate;

public record TeamSearchRequest(String title,
                                String description,
                                Integer memberNumber,
                                String domain,
                                TeamCategory teamCategory,
                                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
                                LocalDate recruitmentStartDate,
                                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
                                LocalDate recruitmentEndDate,
                                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
                                LocalDate projectStartDate,
                                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
                                LocalDate projectEndDate,
                                String searchTerm,
                                String sortOption) {
    public TeamSearchDto toDto() {
        return TeamSearchDto.create(
                this.title,
                this.description,
                this.memberNumber,
                this.domain,
                this.teamCategory,
                this.recruitmentStartDate,
                this.recruitmentEndDate,
                this.projectStartDate,
                this.projectEndDate,
                this.searchTerm,
                this.sortOption
        );
    }

}