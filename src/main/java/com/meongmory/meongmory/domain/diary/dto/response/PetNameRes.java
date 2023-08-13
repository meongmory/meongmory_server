package com.meongmory.meongmory.domain.diary.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
public class PetNameRes {
  private String petName;

  @Builder
  public PetNameRes(String petName) {
    this.petName = petName;
  }

  public static PetNameRes toDto(String petName) {
    return PetNameRes.builder()
        .petName(petName)
        .build();
  }
}
