package com.meongmory.meongmory.domain.diary.dto.response;

import com.meongmory.meongmory.domain.diary.entity.Diary;
import com.meongmory.meongmory.domain.diary.entity.DiaryPet;
import com.meongmory.meongmory.domain.family.entity.Pet;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ListTypeRes {

  private Long diaryId;
  private List<PetNameRes> petName;
  private LocalDateTime uploadedAt;
  private List<FileRes> files;

  @Builder
  public ListTypeRes(Long diaryId, List<PetNameRes> petName, LocalDateTime uploadedAt, List<FileRes> files) {
    this.diaryId = diaryId;
    this.petName = petName;
    this.uploadedAt = uploadedAt;
    this.files = files;
  }

  public static ListTypeRes toDto(Diary diary) {
    return ListTypeRes.builder()
            .petName(diary.getDiaryPets().stream().map(m -> PetNameRes.toDto(m.getPet().getName())).collect(Collectors.toList()))
            .diaryId(diary.getDiaryId())
            .uploadedAt(diary.getCreatedAt())
            .files(diary.getFiles().stream().map(FileRes::toDto).collect(Collectors.toList()))
            .build();
  }
}
