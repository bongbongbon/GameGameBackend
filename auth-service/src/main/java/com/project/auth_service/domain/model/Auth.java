package com.project.auth_service.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE p_store SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
@Table(name = "p_users")
public class Auth extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column( nullable = false, length = 50)
    private String nickName;

    @Column( nullable = false, length = 50)
    private String phoneNumber;

    @Column(nullable = false)
    private UserRole userRole;

    public static Auth create(String email,
                              String password,
                              String nickName,
                              String phoneNumber,
                              UserRole userRole)
    {

        Auth auth = new Auth();
        auth.email = email;
        auth.password = password;
        auth.nickName = nickName;
        auth.phoneNumber = phoneNumber;
        auth.userRole = userRole;
        return auth;
    }

    public void update(String email,
                       String password,
                       String nickName,
                       String phoneNumber) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
    }
    public void updateUserProfile(String email,
                                   String nickName,
                                   String phoneNumber) {
        this.email = email;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
    }

    public void delete() {
        this.isDeleted = true;
    }
}
