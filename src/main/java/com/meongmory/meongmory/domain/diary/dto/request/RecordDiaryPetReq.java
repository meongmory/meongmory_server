package com.meongmory.meongmory.domain.diary.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class RecordDiaryPetReq {

  @NotBlank(message = "P0001")
  @Schema(description = "반려동물 아이디", example = "1")
  private Long petId;
}
