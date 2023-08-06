package com.meongmory.meongmory.domain.diary.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.meongmory.meongmory.domain.diary.entity.Diary;
import com.meongmory.meongmory.domain.diary.entity.SortType;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class GetDiariesRes {
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<GalleryTypeRes> galleryTypeRes;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<ListTypeRes> listTypeRes;

  public static GetDiariesRes toDto(List<Diary> diaries, SortType sortType) {
    GetDiariesRes getDiariesRes = new GetDiariesRes();
    if(sortType == SortType.GALLERY) {
      getDiariesRes.galleryTypeRes = diaries.stream().map(GalleryTypeRes::toDto).collect(Collectors.toList());
    } else {
      getDiariesRes.listTypeRes = diaries.stream().map(ListTypeRes::toDto).collect(Collectors.toList());
    }
    return getDiariesRes;
  }
}
