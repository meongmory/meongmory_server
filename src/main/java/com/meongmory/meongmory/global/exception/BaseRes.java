package com.meongmory.meongmory.global.exception;

import io.swagger.v3.oas.annotations.media.Schema;

public class BaseRes {

    @Schema(description = "상태 코드")
    private final int status;
    @Schema(description = "상태 메시지")
    private final String message;

    public BaseRes(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}