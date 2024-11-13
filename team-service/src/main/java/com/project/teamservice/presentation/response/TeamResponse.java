package com.project.teamservice.presentation.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.teamservice.domain.model.Team;
import com.project.teamservice.domain.model.TeamCategory;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record TeamResponse(Long id,
                           Long userId,
                           String title,
                           String description,
                           Integer memberNumber,
                           String domain,
                           String teamCategory,
                           @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
                           LocalDate recruitmentStartDate,
                           @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
                           LocalDate recruitmentEndDate,
                           @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
                           LocalDate projectStartDate,
                           @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
                           LocalDate projectEndDate) {


    public static TeamResponse of(Team team) {
        return new TeamResponse(
                team.getId(),
                team.getUserId(),
                team.getTitle(),
                team.getDescription(),
                team.getMemberNumber(),
                team.getDomain(),
                mapTeamCategory(team.getTeamCategory()),
                team.getRecruitmentStartDate(),
                team.getRecruitmentEndDate(),
                team.getProjectStartDate(),
                team.getProjectEndDate()
        );
    }

    public static List<TeamResponse> of(List<Team> teams) {
        return teams.stream()
                .map(TeamResponse::of)
                .collect(Collectors.toList());
    }

    // Mapping method to convert TeamCategory to its display value
    private static String mapTeamCategory(TeamCategory category) {
        if (category == null) return null;
        switch (category) {
            case SIDE_JOB: return "사이드잡";
            case SHORT_PROJECT: return "단기 프로젝트";
            case COMPETITION: return "공모전";
            case HACKATHON: return "해커톤";
            // Add more cases if there are other categories to convert
            default: return category.name(); // Default to the original name
        }
    }
}
