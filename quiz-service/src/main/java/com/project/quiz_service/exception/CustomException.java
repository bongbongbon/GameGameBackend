package com.project.quiz_service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException{

    public static final CustomException QUIZ_NOT_FOUND=new CustomException(ErrorCode.QUIZ_NOT_FOUND);

    private final ErrorCode errorCode;
    public HttpStatus getHttpStatus() {
        return errorCode.getHttpStatus();}

}
