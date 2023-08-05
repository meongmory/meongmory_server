package com.meongmory.meongmory.domain.family.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class FamilyPet {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long familyPetId;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name="familyId")
  private Family family;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name="petId")
  private Pet pet;

  @Builder
  public FamilyPet(Family family, Pet pet) {
    this.family = family;
    this.pet = pet;
  }
}
