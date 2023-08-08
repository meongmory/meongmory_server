package com.meongmory.meongmory.domain.diary.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class RecordCommentReq {

  @NotBlank(message = "D0007")
  @Schema(description = "다이어리 댓글", example = "강아지가 너무 귀엽네요 ㅎㅎ")
  private String comment;
}
