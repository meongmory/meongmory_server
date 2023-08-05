package com.meongmory.meongmory.domain.user.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("id에 해당하는 유저를 찾을 수 없습니다.");
    }
}
