package com.meongmory.meongmory.global.response;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.meongmory.meongmory.global.exception.BaseResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class ResponseCustom<T>{

    @Schema(description = "HTTP 상태")
    private final int status;

    @Schema(description = "상태 코드")
    private final String code;

    @Schema(description = "상태 메시지")
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(description = "응답 데이터")
    private T data;

    public static ResponseCustom OK() {
        BaseResponseCode baseResponseCode = BaseResponseCode.SUCCESS;
        return new ResponseCustom<>(baseResponseCode.getStatus().value(), baseResponseCode.getCode(), baseResponseCode.getMessage());
    }

    public static <T> ResponseCustom<T> OK(@Nullable T data) {
        BaseResponseCode baseResponseCode = BaseResponseCode.SUCCESS;
        return new ResponseCustom<T>(baseResponseCode.getStatus().value(), baseResponseCode.getCode(), baseResponseCode.getMessage(), data);
    }

    public static ResponseCustom error(int status, String code, String message) {
        return new ResponseCustom<>(status, code, message);
    }

}
