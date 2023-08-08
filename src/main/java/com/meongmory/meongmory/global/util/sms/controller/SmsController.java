package com.meongmory.meongmory.global.util.sms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.meongmory.meongmory.domain.user.dto.response.SignUpUserRes;
import com.meongmory.meongmory.global.exception.BaseException;
import com.meongmory.meongmory.global.exception.BaseResponseCode;
import com.meongmory.meongmory.global.response.ResponseCustom;
import com.meongmory.meongmory.global.util.sms.dto.SmsSendReq;
import com.meongmory.meongmory.global.util.sms.dto.SmsSendRes;
import com.meongmory.meongmory.global.util.sms.dto.SmsValidationReq;
import com.meongmory.meongmory.global.util.sms.service.SmsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(value = "/sms")
@RequiredArgsConstructor
public class SmsController {
    private final SmsService smsService;

    @Operation(summary = "sms 문자인증", description = "sms 문자인증을 진행한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(S0001)sms 문자인증 성공", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
    })
    @PostMapping("/send")
    public ResponseCustom<SmsSendRes> send(@RequestBody @Valid SmsSendReq smsSendReq) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException, URISyntaxException {
        return ResponseCustom.OK(SmsSendRes.builder()
                .value(smsService.send(smsSendReq))
                .build());
    }

    @Operation(summary = "인증번호 유효시간 검사", description = "인증번호 유효성을 검사합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(S0001) 인증번호 유효검사 성공", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
            @ApiResponse(responseCode = "404", description = "(SM0003) 인증번호 불일치 혹은 만료", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
    })
    @PostMapping("/validate")
    public ResponseCustom<Boolean> validateCode(@RequestBody @Valid SmsValidationReq smsValidationReq) {
        boolean isValid = smsService.validateCode(smsValidationReq.getPhone(), smsValidationReq.getCode());

        if (!isValid) {
            throw new BaseException(BaseResponseCode.SMS_EXPIRATION);
        }
        return ResponseCustom.OK(isValid);
    }

}
