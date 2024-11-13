package com.project.resumeservice.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE p_store SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
@Table(name = "p_career")
public class Career extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String company;

    private String position;

    private String department;

    private String job;

    private String startDate;

    private String endDate;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;


    public static Career create(Long userId,
                                String company,
                                String position,
                                String department,
                                String job,
                                String startDate,
                                String endDate,
                                String description) {

        Career career = new Career();
        career.userId = userId;
        career.company = company;
        career.position = position;
        career.department = department;
        career.job = job;
        career.startDate = startDate;
        career.endDate = endDate;
        career.description = description;
        return career;
    }

    public void update(Long userId,
                                String company,
                                String position,
                                String department,
                                String job,
                                String startDate,
                                String endDate,
                                String description) {
        this.userId = userId;
        this.company = company;
        this.position = position;
        this.department = department;
        this.job = job;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;

    }
}
