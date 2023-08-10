package com.meongmory.meongmory.domain.diary.entity;

import com.meongmory.meongmory.domain.family.entity.Pet;
import com.meongmory.meongmory.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
public class DiaryPet extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long diaryPetId;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name="diaryId")
  private Diary diary;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name="petId")
  private Pet pet;

  @Builder
  public DiaryPet(Diary diary, Pet pet) {
    this.diary = diary;
    this.pet = pet;
    diary.getDiaryPets().add(this);
  }

  public static DiaryPet toEntity(Diary diary, Pet pet) {
  	return DiaryPet.builder()
  			.diary(diary)
  			.pet(pet)
  			.build();
  }
}
