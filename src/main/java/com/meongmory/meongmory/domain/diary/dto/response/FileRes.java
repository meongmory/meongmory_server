package com.meongmory.meongmory.domain.diary.dto.response;

import com.meongmory.meongmory.domain.diary.entity.DiaryFile;
import com.meongmory.meongmory.global.util.AwsS3ImageUrlUtil;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

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
    return FileRes.builder()
            .fileKey(AwsS3ImageUrlUtil.toUrl(diaryFile.getFileKey()))
            .fileType(diaryFile.getType().name())
            .build();
  }
}
