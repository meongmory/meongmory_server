package com.meongmory.meongmory.domain.family.exception;

public class FamilyNotFoundException extends RuntimeException{
    public FamilyNotFoundException() {
        super("id에 해당하는 가족을 찾을 수 없습니다.");
    }
}
