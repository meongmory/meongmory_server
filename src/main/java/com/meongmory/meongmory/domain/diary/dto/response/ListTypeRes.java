package com.meongmory.meongmory.domain.diary.dto.response;

import com.meongmory.meongmory.domain.diary.entity.Diary;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ListTypeRes {

  private Long diaryId;
  private String petName;
  private LocalDateTime uploadedAt;
  private List<FileRes> files;

  public static ListTypeRes toDto(Diary diary) {
    ListTypeRes listTypeRes = new ListTypeRes();
    listTypeRes.diaryId = diary.getDiaryId();
    listTypeRes.petName = diary.getPet().getName();
    listTypeRes.uploadedAt = diary.getCreatedAt();
    listTypeRes.files = diary.getFiles().stream().map(FileRes::toDto).collect(Collectors.toList());
    return listTypeRes;
  }
}
