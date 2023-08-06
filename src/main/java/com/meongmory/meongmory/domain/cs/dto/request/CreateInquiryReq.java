package com.meongmory.meongmory.domain.cs.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CreateInquiryReq {

    @NotBlank(message = "이메일을 입력해 주세요.")
    @Email(message = "올바른 이메일 형식으로 입력해 주세요.")
    @Schema(description="답변 받을 이메일", example = "meong@mju.ac.kr")
    private String email;

    @NotNull(message = "내용을 입력해 주세요.")
    @Schema(description="내용", example = "투자하려고 연락 남깁니다.")
    private String content;
}
