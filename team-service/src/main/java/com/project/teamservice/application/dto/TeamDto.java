package com.project.teamservice.application.dto;

import com.project.teamservice.domain.model.TeamCategory;

import java.time.LocalDate;

public record TeamDto(String title,
                      String description,
                      Integer memberNumber,
                      String domain,
                      TeamCategory teamCategory,
                      LocalDate recruitmentStartDate,
                      LocalDate recruitmentEndDate,
                      LocalDate projectStartDate,
                      LocalDate projectEndDate
) {
    public static TeamDto create(String title,
                                 String description,
                                 Integer memberNumber,
                                 String domain,
                                 TeamCategory teamCategory,
                                 LocalDate recruitmentStartDate,
                                 LocalDate recruitmentEndDate,
                                 LocalDate projectStartDate,
                                 LocalDate projectEndDate) {
        return new TeamDto(
                title,
                description,
                memberNumber,
                domain,
                teamCategory,
                recruitmentStartDate,
                recruitmentEndDate,
                projectStartDate,
                projectEndDate);
    }
}
