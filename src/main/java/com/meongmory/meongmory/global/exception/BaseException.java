package com.meongmory.meongmory.global.exception;

public class BaseException extends RuntimeException {
    private BaseResponseCode baseResponseCode;

    public BaseException(BaseResponseCode baseResponseCode) {
        this.baseResponseCode = baseResponseCode;
    }

    public BaseResponseCode getBaseResponseCode() {
        return baseResponseCode;
    }
}