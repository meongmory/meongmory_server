package com.meongmory.meongmory.domain.diary.dto.assembler;

import com.meongmory.meongmory.domain.diary.entity.Diary;
import com.meongmory.meongmory.domain.diary.entity.Scope;
import com.meongmory.meongmory.domain.family.entity.MemberType;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DiaryAssembler {


  public List<Diary> filteredDiariesByScope(List<Diary> diaries, MemberType type) {
    List<Diary> inScopeDiaries = new ArrayList<>();
    for (Diary diary : diaries) {
      if(checkScope(diary.getScope(), type)) inScopeDiaries.add(diary);
    }
    return inScopeDiaries;
  }

  private boolean checkScope(Scope scope, MemberType type) {
    return scope.name().split("_")[1].equals(type.name());
  }
}
