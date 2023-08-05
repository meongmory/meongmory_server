package com.meongmory.meongmory.global.exception;

import org.springframework.http.HttpStatus;

public enum BaseResponseCode {
    SUCCESS(HttpStatus.OK, "요청에 성공했습니다."),
    ALREADY_PHONENUM(HttpStatus.BAD_REQUEST, "이미 존재하는 번호입니다."),
    TOKEN_NOT_VALID(HttpStatus.BAD_REQUEST, "해당 토큰은 유효하지 않습니다."),
    TOKEN_EXPIRATION(HttpStatus.BAD_REQUEST, "토큰이 만료되었습니다.");




    private final HttpStatus status;
    private final String message;

    BaseResponseCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}