package com.project.teamservice.application.dto;

import com.project.teamservice.domain.model.TeamCategory;

import java.time.LocalDate;

public record TeamSearchDto(String title,
                            String description,
                            Integer memberNumber,
                            String domain,
                            TeamCategory teamCategory,
                            LocalDate recruitmentStartDate,
                            LocalDate recruitmentEndDate,
                            LocalDate projectStartDate,
                            LocalDate projectEndDate,
                            String searchTerm,
                            String sortOption) {

    public static TeamSearchDto create(String title,
                                     String description,
                                     Integer memberNumber,
                                     String domain,
                                     TeamCategory teamCategory,
                                     LocalDate recruitmentStartDate,
                                     LocalDate recruitmentEndDate,
                                     LocalDate projectStartDate,
                                     LocalDate projectEndDate,
                                       String searchTerm,
                                       String sortOption) {
        return new TeamSearchDto(
                title,
                description,
                memberNumber,
                domain,
                teamCategory,
                recruitmentStartDate,
                recruitmentEndDate,
                projectStartDate,
                projectEndDate,
                searchTerm,
                sortOption);
    }
}
