package com.meongmory.meongmory.global.exception;

import org.springframework.http.HttpStatus;

public enum BaseResponseCode {
    SUCCESS(HttpStatus.OK, "요청에 성공했습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "id에 해당하는 유저가 존재하지 않습니다."),
    FAMILY_NOT_FOUND(HttpStatus.NOT_FOUND, "id에 해당하는 가족이 존재하지 않습니다."),
    FAMILY_MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "id에 해당하는 가족 구성원이 존재하지 않습니다."),
    PET_NOT_FOUND(HttpStatus.NOT_FOUND, "id에 해당하는 반려동물이 존재하지 않습니다."),
    SORT_TYPE_NOT_FOUND(HttpStatus.NOT_FOUND, "SortType을 찾을 수 없습니다."),
    DIARY_NOT_FOUND(HttpStatus.NOT_FOUND, "id에 해당하는 일기가 존재하지 않습니다."),
    INVALID_SCOPE(HttpStatus.FORBIDDEN, "해당 일기를 볼 수 있는 권한이 없습니다.");

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
