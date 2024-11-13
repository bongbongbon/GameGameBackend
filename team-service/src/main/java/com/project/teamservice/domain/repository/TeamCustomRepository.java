package com.project.teamservice.domain.repository;

import com.project.teamservice.application.dto.TeamDto;
import com.project.teamservice.application.dto.TeamSearchDto;
import com.project.teamservice.domain.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeamCustomRepository {
    Page<Team> searchTeams(TeamSearchDto request, Pageable pageable);
}
