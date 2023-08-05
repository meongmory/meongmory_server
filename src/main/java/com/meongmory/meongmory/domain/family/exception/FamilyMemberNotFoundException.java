package com.meongmory.meongmory.domain.family.exception;

public class FamilyMemberNotFoundException extends RuntimeException{
    public FamilyMemberNotFoundException() {
        super("가족 구성원이 아닙니다.");
    }
}
