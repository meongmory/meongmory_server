package com.meongmory.meongmory.domain.diary.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.meongmory.meongmory.domain.diary.entity.Diary;
import com.meongmory.meongmory.domain.diary.entity.SortType;
import com.meongmory.meongmory.domain.family.entity.Pet;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class GetDiariesRes {
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<GalleryTypeRes> galleryTypeRes;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<ListTypeRes> listTypeRes;

  public static GetDiariesRes toDto(List<Diary> diaries, Pet pet, SortType sortType) {
    GetDiariesRes getDiariesRes = new GetDiariesRes();
    if(sortType == SortType.GALLERY) {
      getDiariesRes.galleryTypeRes = diaries.stream().map(GalleryTypeRes::toDto).collect(Collectors.toList());
    } else {
      getDiariesRes.listTypeRes = diaries.stream().map(diary -> ListTypeRes.toDto(diary, pet)).collect(Collectors.toList());
    }
    return getDiariesRes;
  }
}
