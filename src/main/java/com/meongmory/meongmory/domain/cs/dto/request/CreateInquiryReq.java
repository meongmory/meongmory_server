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
    @NotBlank(message = "G0002")
    @Email(message = "G0003")
    @Schema(type = "String", description="답변 받을 이메일", example = "meong@mju.ac.kr")
    private String email;

    @NotNull(message = "G0004")
    @Schema(type = "String", description="내용", example = "투자하려고 연락 남깁니다.")
    private String content;
}
