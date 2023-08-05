package com.meongmory.meongmory.domain.family.entity;

import com.meongmory.meongmory.domain.diary.entity.Diary;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class FamilyDiary {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long familyDiaryId;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name="familyId")
  private Family family;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name="diaryId")
  private Diary diary;

  @Builder
  public FamilyDiary(Family family, Diary diary) {
    this.family = family;
    this.diary = diary;
  }
}
