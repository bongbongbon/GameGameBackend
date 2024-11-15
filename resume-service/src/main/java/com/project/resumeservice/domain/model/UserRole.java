package com.project.resumeservice.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
public enum UserRole {
    ADMIN("Admin"),
    USER("User");

    private final String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

}