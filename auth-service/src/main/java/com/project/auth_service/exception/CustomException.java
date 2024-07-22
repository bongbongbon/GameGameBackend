package com.project.auth_service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException{

    public static final CustomException ALREADY_USER_EXISTS = new CustomException(ErrorCode.ALREADY_USER_EXISTS);
    public static final CustomException USER_NOT_FOUND = new CustomException(ErrorCode.USER_NOT_FOUND);
    public static final CustomException PASSWORD_NOT_MATCH = new CustomException(ErrorCode.PASSWORD_NOT_MATCH);
    public static final CustomException INTERNAL_SERVER_ERROR = new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
    public static final CustomException VALIDATION_FAILED = new CustomException(ErrorCode.VALIDATION_FAILED);



    private final ErrorCode errorCode;

    public HttpStatus getHttpStatus() {
        return errorCode.getHttpStatus();
    }
}
