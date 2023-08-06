package com.meongmory.meongmory.domain.diary.dto.response;

import com.meongmory.meongmory.domain.diary.entity.Diary;
import lombok.Builder;
import lombok.Data;

@Data
public class GalleryTypeRes {
  private Long diaryId;
  private String thumbnailImgKey;

  @Builder
  public GalleryTypeRes(Long diaryId, String thumbnailImgKey) {
    this.diaryId = diaryId;
    this.thumbnailImgKey = thumbnailImgKey;
  }

  public static GalleryTypeRes toDto(Diary diary) {
    return GalleryTypeRes.builder()
        .diaryId(diary.getDiaryId())
        .thumbnailImgKey(diary.getFiles().get(0).getFileKey())
        .build();
  }
}
