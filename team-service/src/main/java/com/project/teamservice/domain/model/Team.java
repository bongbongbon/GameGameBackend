package com.project.teamservice.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE p_store SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
@Table(name = "p_team")
public class Team extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String title;

    @Column(length = 10000)  // 필요에 따라 길이 조정
    private String description;

    private Integer memberNumber;

    private String domain;

    @Enumerated(EnumType.STRING)  // Enum을 문자열로 DB에 저장
    private TeamCategory teamCategory;

    // 모집 기간 필드
    private LocalDate recruitmentStartDate;
    private LocalDate recruitmentEndDate;

    // 프로젝트 진행 기간 필드
    private LocalDate projectStartDate;
    private LocalDate projectEndDate;

    public static Team create(Long userId,
                              String title,
                              String description,
                              Integer memberNumber,
                              String domain,
                              TeamCategory teamCategory,
                              LocalDate recruitmentStartDate,
                              LocalDate recruitmentEndDate,
                              LocalDate projectStartDate,
                              LocalDate projectEndDate)
    {

        Team team = new Team();
        team.userId = userId;
        team.title = title;
        team.description = description;
        team.memberNumber = memberNumber;
        team.domain = domain;
        team.teamCategory = teamCategory;
        team.recruitmentStartDate = recruitmentStartDate;
        team.recruitmentEndDate = recruitmentEndDate;
        team.projectStartDate = projectStartDate;
        team.projectEndDate = projectEndDate;
        return team;
    }

    public void update(String title,
                       String description,
                       Integer memberNumber,
                       String domain,
                       TeamCategory teamCategory,
                       LocalDate recruitmentStartDate,
                       LocalDate recruitmentEndDate,
                       LocalDate projectStartDate,
                       LocalDate projectEndDate) {
        this.title = title;
        this.description = description;
        this.memberNumber = memberNumber;
        this.domain = domain;
        this.teamCategory = teamCategory;
        this.recruitmentStartDate = recruitmentStartDate;
        this.recruitmentEndDate = recruitmentEndDate;
        this.projectStartDate = projectStartDate;
        this.projectEndDate = projectEndDate;
    }

    public void delete() {
        this.isDeleted = true;
    }
}
