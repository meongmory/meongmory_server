package com.meongmory.meongmory.domain.diary.dto.response;

import com.meongmory.meongmory.domain.diary.entity.DiaryFile;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class FileRes {

  private String fileKey;
  private String fileType;

  @Builder
  public FileRes(String fileKey, String fileType) {
    this.fileKey = fileKey;
    this.fileType = fileType;
  }

  public static FileRes toDto(DiaryFile diaryFile) {
//    FileRes fileRes = new FileRes();
//    fileRes.fileKey = diaryFile.getFileKey();
//    fileRes.fileType = diaryFile.getType().name();
//    return fileRes;
    return FileRes.builder()
        .fileKey(diaryFile.getFileKey())
        .fileType(diaryFile.getType().name())
        .build();
  }
}
