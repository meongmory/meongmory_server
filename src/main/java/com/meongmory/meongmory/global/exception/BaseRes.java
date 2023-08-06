package com.meongmory.meongmory.global.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseRes {
    @Schema(description = "상태 코드", allowableValues = {"400", "404", "500"})
    public final int status;
    @Schema(description = "상태 메시지")
    public final String message;
    @Schema(description = "코드")
    public final String code;
}