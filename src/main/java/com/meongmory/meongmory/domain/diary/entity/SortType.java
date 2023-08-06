package com.meongmory.meongmory.domain.diary.entity;

import com.meongmory.meongmory.global.exception.BaseException;
import com.meongmory.meongmory.global.exception.BaseResponseCode;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum SortType {
  GALLERY("갤러리형식"),
  LIST("리스트형식");

  private final String name;

  private SortType(String name) {
    this.name = name;
  }

  public static SortType getSortTypeByName(String name){
    return Arrays.stream(SortType.values())
            .filter(r -> r.getName().equals(name))
            .findAny().orElseThrow(() -> new BaseException(BaseResponseCode.SORT_TYPE_NOT_FOUND));
  }
}
