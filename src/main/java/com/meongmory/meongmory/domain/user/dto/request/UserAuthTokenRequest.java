package com.meongmory.meongmory.domain.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Getter
public class UserAuthTokenRequest {
    @NotNull(message="TOKEN_NULL")
    @Schema(type = "String", example = "1", description = "userId")
    private String accessToken;

    @NotNull(message="TOKEN_NULL")
    @Schema(type = "String", example = "010", description = "phone-num")
    private String refreshToken;
}
