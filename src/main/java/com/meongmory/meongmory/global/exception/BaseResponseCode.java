package com.meongmory.meongmory.global.exception;

import org.springframework.http.HttpStatus;

public enum BaseResponseCode {
    SUCCESS(HttpStatus.OK, "요청에 성공했습니다."),

    // cs
    INVALID_NOTICE_ID(HttpStatus.BAD_REQUEST, "존재하지 않는 공지사항입니다.");

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