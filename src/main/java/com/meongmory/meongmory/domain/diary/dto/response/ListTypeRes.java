package com.meongmory.meongmory.domain.diary.dto.response;

import com.meongmory.meongmory.domain.diary.entity.Diary;
import lombok.Builder;
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

  @Builder
  public ListTypeRes(Long diaryId, String petName, LocalDateTime uploadedAt, List<FileRes> files) {
    this.diaryId = diaryId;
    this.petName = petName;
    this.uploadedAt = uploadedAt;
    this.files = files;
  }

  public static ListTypeRes toDto(Diary diary) {
    return ListTypeRes.builder()
        .diaryId(diary.getDiaryId())
        .petName(diary.getPet().getName())
        .uploadedAt(diary.getCreatedAt())
        .files(diary.getFiles().stream().map(FileRes::toDto).collect(Collectors.toList()))
        .build();
  }
}
