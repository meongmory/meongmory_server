package com.meongmory.meongmory.domain.diary.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.meongmory.meongmory.domain.diary.entity.Diary;
import com.meongmory.meongmory.domain.diary.entity.DiaryComment;
import com.meongmory.meongmory.domain.family.entity.Pet;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DetailDiaryRes {
  private List<String> names;
  private LocalDateTime uploadedAt;
  private List<FileRes> files;
  private String content;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<CommentRes> comments;

  @Builder
  public DetailDiaryRes(List<String> names, LocalDateTime uploadedAt, List<FileRes> files, String content, List<CommentRes> comments) {
    this.names = names;
    this.uploadedAt = uploadedAt;
    this.files = files;
    this.content = content;
    this.comments = comments;
  }

  public static DetailDiaryRes toDto(Diary diary, List<Pet> pets, List<DiaryComment> comments) {
    return DetailDiaryRes.builder()
            .names(pets.stream().map(Pet::getName).collect(Collectors.toList()))
            .uploadedAt(diary.getCreatedAt())
            .files(diary.getFiles().stream().map(FileRes::toDto).collect(Collectors.toList()))
            .content(diary.getContent())
            .comments(comments.stream().map(CommentRes::toDto).collect(Collectors.toList()))
            .build();
  }
}
