package com.meongmory.meongmory.domain.family.exception;

public class PetNotFoundException extends RuntimeException{
    public PetNotFoundException() {
        super("id에 해당하는 반려동물을 찾을 수 없습니다.");
    }
}
