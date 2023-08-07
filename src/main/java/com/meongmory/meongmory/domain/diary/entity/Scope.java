package com.meongmory.meongmory.domain.diary.entity;

import com.meongmory.meongmory.global.exception.BaseException;
import com.meongmory.meongmory.global.exception.BaseResponseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Scope {
    PUBLIC_FAMILY(""),
    PUBLIC_FRIENDS("");

    private final String name;

    public static Scope getScopeByName(String name){
        return Arrays.stream(Scope.values())
                .filter(r -> r.getName().equals(name))
                .findAny().orElseThrow(() -> new BaseException(BaseResponseCode.SCOPE_NOT_FOUND));
    }
}
