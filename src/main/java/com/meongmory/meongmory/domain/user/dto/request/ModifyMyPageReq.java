package com.meongmory.meongmory.domain.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ModifyMyPageReq {
    @NotNull(message="U0003")
    @Schema(type = "string", example = "이승학", description = "닉네임")
    private String nickname;
}
