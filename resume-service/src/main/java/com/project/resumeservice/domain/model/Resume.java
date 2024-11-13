package com.project.resumeservice.domain.model;

import com.project.resumeservice.application.dto.CareerDto;
import com.project.resumeservice.presentation.request.CareerCreateRequest;
import com.project.resumeservice.presentation.response.CareerResponse;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE p_store SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
@Table(name = "p_resume")
public class Resume extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String title;

    @ElementCollection
    private List<String> selectedJobs;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Career> careers = new ArrayList<>();

    public static Resume create(Long userId,
                                String title,
                                List<String> selectedJobs,
                                List<Career> careers)
    {

        Resume resume = new Resume();
        resume.userId = userId;
        resume.title = title;
        resume.selectedJobs = selectedJobs;
        resume.careers = careers;
        return resume;
    }

    public void update(String title,
                       List<String> selectedJobs) {

        this.title = title;
        this.selectedJobs = selectedJobs;
    }

    public void delete() {
        this.isDeleted = true;
    }

}
