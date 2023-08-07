package com.meongmory.meongmory.domain.diary.entity;

import com.meongmory.meongmory.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class DiaryFile extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long diaryFileId;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "diaryId")
  private Diary diary;

  private String fileKey;

  @Enumerated(EnumType.STRING)
  private FileType type;

  @Builder
  public DiaryFile(Diary diary, String fileKey, FileType type) {
    this.diary = diary;
    this.fileKey = fileKey;
    this.type = type;
  }

  public static DiaryFile toEntity(Diary diary, String fileKey, FileType type) {
    DiaryFile diaryFile = DiaryFile.builder()
            .diary(diary)
            .fileKey(fileKey)
            .type(type)
            .build();
    diary.getFiles().add(diaryFile);
    return diaryFile;
  }
}
