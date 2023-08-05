package com.meongmory.meongmory.domain.diary.exception;

public class SortTypeNotFoundException extends RuntimeException{
    public SortTypeNotFoundException() {
        super("SortType을 찾을 수 없습니다.");
    }
}
