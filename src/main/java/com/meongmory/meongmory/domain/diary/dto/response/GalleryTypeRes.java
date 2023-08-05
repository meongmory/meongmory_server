package com.meongmory.meongmory.domain.diary.dto.response;

import com.meongmory.meongmory.domain.diary.entity.Diary;
import lombok.Data;

@Data
public class GalleryTypeRes {
  private Long diaryId;
  private String thumbnailImgKey;

  public static GalleryTypeRes toDto(Diary diary) {
    GalleryTypeRes galleryTypeRes = new GalleryTypeRes();
    galleryTypeRes.diaryId = diary.getDiaryId();
    galleryTypeRes.thumbnailImgKey = diary.getFiles().get(0).getFileKey();
    return galleryTypeRes;
  }
}
