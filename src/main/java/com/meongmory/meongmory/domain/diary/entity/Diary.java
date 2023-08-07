package com.meongmory.meongmory.domain.diary.entity;

import com.meongmory.meongmory.domain.family.entity.Family;
import com.meongmory.meongmory.domain.family.entity.Pet;
import com.meongmory.meongmory.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Diary extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long diaryId;

  @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL)
  private List<DiaryFile> files = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name="familyId")
  private Family family;

  private String title;
  private String content;

  @Enumerated(EnumType.STRING)
  private Scope scope;

  @Builder
  public Diary(Family family, String title, String content, Scope scope) {
    this.family = family;
    this.title = title;
    this.content = content;
    this.scope = scope;
  }

  public static Diary toEntity(Family family, String title, String content, Scope scope) {
    return Diary.builder()
            .family(family)
            .title(title)
            .content(content)
            .scope(scope)
            .build();
  }
}
