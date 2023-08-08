package com.meongmory.meongmory.global.util.sms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SmsValidationReq {
    @Schema(type = "String", description = "전화번호", example = "010-0000-0000")
    @Size(min = 11, max = 13, message = "SM0004")
    @NotBlank(message = "SM0001")
    private String phone;

    @Schema(type = "String", description = "전화번호", example = "12345")
    @Size(min = 1, max = 10, message = "SM0005")
    @NotBlank(message = "SM0002")
    private String code;
}
