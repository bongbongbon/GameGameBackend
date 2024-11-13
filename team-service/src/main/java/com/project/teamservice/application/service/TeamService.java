package com.project.teamservice.application.service;

import com.project.teamservice.application.dto.TeamDto;
import com.project.teamservice.application.dto.TeamSearchDto;
import com.project.teamservice.domain.model.Team;
import com.project.teamservice.domain.model.UserRole;
import com.project.teamservice.domain.repository.TeamRepository;
import com.project.teamservice.presentation.response.TeamResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
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


}
