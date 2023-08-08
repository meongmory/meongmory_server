package com.meongmory.meongmory.domain.diary.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class RecordDiaryReq {

  @NotNull(message = "F0001")
  @Schema(description = "가족 아이디", example = "1")
  private Long familyId;

  @NotNull(message = "P0001")
  @Schema(description = "반려동물 아이디 리스트", example = "[{\"petId\" : 1}, {\"petId\" : 2}]")
  private List<RecordDiaryPetReq> pets;

  @NotNull(message = "F0002")
  @Schema(description = "다이어리 파일 키 리스트", example = "[{\"fileKey\": \"dog.png\", \"fileType\": \"photo\"}, {\"fileKey\": \"cat.mov\", \"fileType\": \"video\"}]")
  private List<RecordDiaryFileKeyReq> files;

  @NotBlank(message = "G0005")
  @Schema(description = "다이어리 제목", example = "다이어리 제목")
  private String title;

  @NotBlank(message = "G0004")
  @Schema(description = "다이어리 내용", example = "다이어리 내용")
  private String content;

  @NotBlank(message = "D0004")
  @Schema(description = "다이어리 공개 범위 (default : public)", example = "family")
  private String scope;

}
