package com.meongmory.meongmory.domain.diary.exception;

public class DiaryNotFoundException extends RuntimeException{
    public DiaryNotFoundException() {
        super("id에 해당하는 기록이 존재하지 않습니다.");
    }
}
