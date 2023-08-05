package com.meongmory.meongmory.domain.diary.dto.response;

import com.meongmory.meongmory.domain.diary.entity.DiaryFile;
import lombok.Data;

import java.util.List;

@Data
public class FileRes {

  private String fileKey;
  private String fileType;

  public static FileRes toDto(DiaryFile diaryFile) {
    FileRes fileRes = new FileRes();
    fileRes.fileKey = diaryFile.getFileKey();
    fileRes.fileType = diaryFile.getType().name();
    return fileRes;
  }
}
