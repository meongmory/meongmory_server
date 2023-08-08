package com.meongmory.meongmory.domain.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SignInUserReq {
    @NotNull(message="SM0001")
    @Schema(type = "string", example = "010-4158-8124", description = "전화번호")
    private String phone;
}
