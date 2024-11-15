package com.project.teamservice.presentation.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.teamservice.application.dto.TeamDto;
import com.project.teamservice.application.service.TeamService;
import com.project.teamservice.domain.model.Team;
import com.project.teamservice.domain.model.TeamCategory;
import com.project.teamservice.domain.model.UserRole;
import com.project.teamservice.domain.repository.TeamRepository;
import com.project.teamservice.presentation.request.TeamCreateRequest;
import com.project.teamservice.presentation.request.TeamSearchRequest;
import com.project.teamservice.presentation.request.TeamUpdateRequest;
import com.project.teamservice.presentation.response.ApiSuccessResponse;
import com.project.teamservice.presentation.response.TeamResponse;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teams")
public class TeamController {


    private final TeamService teamService;
    private final TeamRepository teamRepository;

    @PostMapping
    public ApiSuccessResponse<TeamResponse> createTeam(@RequestHeader(name = "X-UserId") Long userId,
                                                       @RequestHeader(name = "X-Role") UserRole userRole,
                                                       @RequestBody TeamCreateRequest createRequest) {


        return ApiSuccessResponse.from(teamService.createTeam(createRequest.toDto(), userId, userRole));
    }

    @GetMapping("/{teamId}")
    public ApiSuccessResponse<?> getTeam(@PathVariable(name = "teamId") Long teamId) {


        return ApiSuccessResponse.from(teamService.getTeam(teamId));
    }

    @GetMapping
    public ApiSuccessResponse<Page<TeamResponse>> getAllTeam(@RequestParam(name = "page", defaultValue = "1") int page,
                                                             @RequestParam(name = "size", defaultValue = "12") int size) {

        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        return ApiSuccessResponse.from(teamService.getAllTeam(pageRequest));
    }


    @GetMapping("/search")
    public ApiSuccessResponse<Page<TeamResponse>> searchTeams(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "domain", required = false) String domain,
            @RequestParam(name = "teamCategory", required = false) TeamCategory teamCategory,
            @RequestParam(name = "memberNumber", required = false) Integer memberNumber,
            @RequestParam(name = "recruitmentStartDate", required = false) @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul") LocalDate recruitmentStartDate,
            @RequestParam(name = "recruitmentEndDate", required = false) @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul") LocalDate recruitmentEndDate,
            @RequestParam(name = "projectStartDate", required = false) @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul") LocalDate projectStartDate,
            @RequestParam(name = "projectEndDate", required = false) @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul") LocalDate projectEndDate,
            @RequestParam(name = "searchTerm", required = false) String searchTerm,
            @RequestParam(name = "sortOption", required = false) String sortOption,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "12") int size
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);


        // OrderSearchRequest 객체 수동 생성
        TeamSearchRequest teamSearchRequest = new TeamSearchRequest(
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
                sortOption
        );



        // 서비스 호출 및 결과 반환 로직
        Page<TeamResponse> teamResponses = teamService.searchTeams(teamSearchRequest.toDto(), pageRequest);
        return ApiSuccessResponse.from(teamResponses);
    }

    @PatchMapping("/{teamId}")
    public ApiSuccessResponse<TeamResponse> updateTeam(@PathVariable(name = "teamId") Long teamId,
                                                       @RequestBody TeamUpdateRequest request) {


        return ApiSuccessResponse.from(teamService.updateTeam(teamId, request.toDto()));
    }

    @DeleteMapping("/{teamId}")
    public ApiSuccessResponse<TeamResponse> deleteTeam(@PathVariable(name = "teamId") Long teamId,
                                                       @RequestBody TeamUpdateRequest request) {


        return ApiSuccessResponse.from(teamService.deleteTeam(teamId));
    }


    @GetMapping("/{teamId}/user")
    public ApiSuccessResponse<?> getTeamAndUser(@PathVariable(name = "teamId") Long teamId) {


        return ApiSuccessResponse.from(teamService.getTeamAndGetUser(teamId));
    }



}
