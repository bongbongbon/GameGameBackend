package com.project.quiz_service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    QUIZ_NOT_FOUND(HttpStatus.BAD_REQUEST, "QUIZ_NOT_FOUND", "존재하지 않는 퀴즈입니다,");



    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
