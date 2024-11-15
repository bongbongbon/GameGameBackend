package com.project.teamservice.presentation.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.teamservice.domain.model.Team;
import com.project.teamservice.domain.model.TeamCategory;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record TeamUserResponse(Long id,
                               Long userId,
                               String nickName,
                               String email,
                               String phoneNumber,
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

}
