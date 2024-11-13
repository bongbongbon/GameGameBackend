package com.project.teamservice.infrastructure.repository;

import com.project.teamservice.application.dto.TeamDto;
import com.project.teamservice.application.dto.TeamSearchDto;
import com.project.teamservice.domain.model.QTeam;
import com.project.teamservice.domain.model.Team;
import com.project.teamservice.domain.repository.TeamCustomRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TeamRepositoryImpl implements TeamCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Team> searchTeams(TeamSearchDto request, Pageable pageable) {

        QTeam team = QTeam.team;

        BooleanBuilder builder = new BooleanBuilder();

        // 제목 및 설명 검색
        if (StringUtils.hasText(request.searchTerm())) {
            builder.and(team.title.containsIgnoreCase(request.searchTerm()) // Check title
                            .or(team.description.containsIgnoreCase(request.searchTerm())
                            .or(team.domain.containsIgnoreCase(request.searchTerm())))); // Check description
        }



        if (request.teamCategory() != null) {
            builder.and(team.teamCategory.eq(request.teamCategory()));
        }

        // Query 생성 및 페이징 처리
        JPAQuery<Team> query = jpaQueryFactory
                .selectFrom(team)
                .where(builder)
                .offset(pageable.getOffset())  // 페이징 시작 위치
                .limit(pageable.getPageSize());  // 페이지 크기


        if(request.sortOption().equals("최신순")) {
            query.orderBy(team.createdAt.desc());
        } else if (request.sortOption().equals("오래된순")) {
            query.orderBy(team.createdAt.asc());
        } else if (request.sortOption().equals("이름순")) {
            query.orderBy(team.title.asc());
        }


        // 결과 조회
        List<Team> results = query.fetch();
        long total = query.fetchCount();

        return new PageImpl<>(results, pageable, total);
    }
}