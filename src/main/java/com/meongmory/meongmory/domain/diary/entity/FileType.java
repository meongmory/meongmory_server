package com.meongmory.meongmory.domain.diary.entity;

import com.meongmory.meongmory.global.exception.BaseException;
import com.meongmory.meongmory.global.exception.BaseResponseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum FileType {
    PHOTO("photo"),
    VIDEO("video");

    private final String name;

    public static FileType getFileTypeByName(String name){
        return Arrays.stream(FileType.values())
                .filter(r -> r.getName().equals(name))
                .findAny().orElseThrow(() -> new BaseException(BaseResponseCode.FILETYPE_NOT_FOUND));
    }
}
