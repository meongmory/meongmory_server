package com.meongmory.meongmory.domain.family.entity;

import com.meongmory.meongmory.global.exception.BaseException;
import com.meongmory.meongmory.global.exception.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE("수컷"),
    FEMALE("암컷"),
    UNKNOWN("미상/중성");
    private final String name;

    public static Gender getGenderByName(String name){
        return Arrays.stream(Gender.values())
                .filter(r -> r.getName().equals(name))
                .findAny().orElseThrow(() -> new BaseException(BaseResponseCode.PET_GENDER_NOT_FOUND));
    }
}
