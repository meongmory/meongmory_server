package com.meongmory.meongmory.domain.diary.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
public class RecordDiaryReq {

  @NotBlank(message = "다이어리를 작성할 가족의 아이디를 입력해주세요.")
  @Schema(description = "가족 아이디", example = "1")
  private Long familyId;

  @NotBlank(message = "최소 한 마리 이상의 반려동물의 아이디를 입력해주세요.")
  @Schema(description = "반려동물 아이디 리스트", example = "[1, 2, 3]")
  private List<RecordDiaryPetReq> pets;

  @NotBlank(message = "최소 한 개 이상의 파일을 입력해주세요.")
  @Schema(description = "다이어리 파일 키 리스트", example = "[{\"fileKey\": \"dog.png\", \"fileType\": \"photo\"}, {\"fileKey\": \"cat.mov\", \"fileType\": \"video\"}]")
  private List<RecordDiaryFileKeyReq> files;

  @NotBlank(message = "다이어리 제목을 입력해주세요.")
  @Schema(description = "다이어리 제목", example = "다이어리 제목")
  private String title;

  @NotBlank(message = "다이어리 내용을 입력해주세요.")
  @Schema(description = "다이어리 내용", example = "다이어리 내용")
  private String content;

  @NotBlank(message = "다이어리 공개 범위를 입력해주세요.")
  @Schema(description = "다이어리 공개 범위 (default : public)", example = "family")
  private String scope;

}
