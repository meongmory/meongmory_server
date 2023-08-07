package com.meongmory.meongmory.global.entity;

import com.meongmory.meongmory.domain.diary.entity.SortType;
import com.meongmory.meongmory.global.exception.BaseException;
import com.meongmory.meongmory.global.exception.BaseResponseCode;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum AnimalType {
    CAT("고양이"),
    DOG("강아지");

    private final String name;

    AnimalType(String name) {
        this.name = name;
    }

    public static AnimalType getAnimalTypeByName(String name){
        return Arrays.stream(AnimalType.values())
                .filter(r -> r.getName().equals(name))
                .findAny().orElseThrow(() -> new BaseException(BaseResponseCode.ANIMAL_NOT_FOUND));
    }
}
