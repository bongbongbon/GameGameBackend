package com.project.resumeservice.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserRole {
    USER(Authority.USER),
    MASTER(Authority.MASTER);

    private final String authority;

    public static class Authority {

        public static final String USER = "ROLE_USER";
        public static final String MASTER = "ROLE_MASTER";
    }

}