package com.project.auth_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.auth_service.exception.ErrorCode;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiErrorResponse {


    @JsonProperty("errorCode")
    private String code;

    @JsonProperty("errorMessage")
    private String message;

    public static ApiErrorResponse from(ErrorCode errorCode) {
        return ApiErrorResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
    }

    public void changeMessage(String message) {
        this.message = message;
    }
}
