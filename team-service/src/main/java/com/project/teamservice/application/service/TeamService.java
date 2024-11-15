package com.project.teamservice.application.service;

import com.project.teamservice.application.dto.TeamDto;
import com.project.teamservice.application.dto.TeamSearchDto;
import com.project.teamservice.domain.model.Team;
import com.project.teamservice.domain.model.UserRole;
import com.project.teamservice.domain.repository.TeamRepository;
import com.project.teamservice.domain.service.UserService;
import com.project.teamservice.presentation.response.TeamResponse;
import com.project.teamservice.presentation.response.TeamUserResponse;
import com.project.teamservice.infrastructure.client.dto.UserResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class TeamService {

    private final TeamRepository teamRepository;

    private final UserService userService;

    public TeamService(TeamRepository teamRepository, UserService userService) {
        this.teamRepository = teamRepository;
        this.userService = userService;
    }

    @Transactional
    public TeamResponse createTeam(TeamDto request, Long userId, UserRole userRole) {


        Team team = Team.create(
                userId,
                request.title(),
                request.description(),
                request.memberNumber(),
                request.domain(),
                request.teamCategory(),
                request.recruitmentStartDate(),
                request.recruitmentEndDate(),
                request.projectStartDate(),
                request.projectEndDate()
        );

        return TeamResponse.of(teamRepository.save(team));
    }

    @Transactional
    public TeamResponse getTeam(Long teamId) {

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("팀 없음"));

        return TeamResponse.of(team);
    }


    public Page<TeamResponse> getAllTeam(PageRequest pageRequest) {

        return teamRepository.findAll(pageRequest)
                .map(TeamResponse::of);
    }

    @Transactional
    public Page<TeamResponse> searchTeams(TeamSearchDto request, PageRequest pageRequest) {

        return teamRepository.searchTeams(request, pageRequest)
                .map(TeamResponse::of);
    }

    @Transactional
    public TeamResponse updateTeam(Long teamId, TeamDto request) {

        return teamRepository.findById(teamId).map(team -> {
            team.update(
                    request.title(),
                    request.description(),
                    request.memberNumber(),
                    request.domain(),
                    request.teamCategory(),
                    request.recruitmentStartDate(),
                    request.recruitmentEndDate(),
                    request.projectStartDate(),
                    request.projectEndDate()
            );
            return TeamResponse.of(team);
        }).orElseThrow(() -> new RuntimeException("팀없음"));

    }

    @Transactional
    public TeamResponse deleteTeam(Long teamId) {

        return teamRepository.findById(teamId).map(team -> {
            team.delete();
            return TeamResponse.of(team);
        }).orElseThrow(() -> new RuntimeException("팀없음"));
    }

    @Transactional
    public TeamUserResponse getTeamAndGetUser(Long teamId) {


        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("팀 없음"));

        UserResponse user = userService.getUser(team.getUserId());

        TeamUserResponse  teamUserResponse = new TeamUserResponse(
                team.getId(),
                team.getUserId(),
                user.nickName(),
                user.email(),
                user.phoneNumber(),
                team.getTitle(),
                team.getDescription(),
                team.getMemberNumber(),
                team.getDomain(),
                team.getTeamCategory().toString(),
                team.getRecruitmentStartDate(),
                team.getRecruitmentEndDate(),
                team.getProjectStartDate(),
                team.getProjectEndDate()
        );

        return teamUserResponse;


    }

}
