package com.meongmory.meongmory.global.exception;

public class BaseRes {
    private final int status;
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