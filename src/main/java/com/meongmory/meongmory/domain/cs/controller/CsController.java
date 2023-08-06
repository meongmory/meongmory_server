package com.meongmory.meongmory.domain.cs.controller;

import com.meongmory.meongmory.domain.cs.dto.request.CreateInquiryReq;
import com.meongmory.meongmory.domain.cs.service.CsService;
import com.meongmory.meongmory.global.exception.BaseRes;
import com.meongmory.meongmory.global.response.ResponseCustom;
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

@RestController
@RequestMapping(value = "/cs")
@RequiredArgsConstructor
public class CsController {

    private final CsService csService;

    @PostMapping("/inquires")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "문의하기 성공", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = BaseRes.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 유저", content = @Content(schema = @Schema(implementation = BaseRes.class)))
    })
    @Operation(summary = "문의하기", description = "이메일 및 내용을 입력 받아 문의사항을 저장합니다.")
    public ResponseCustom createInquiry(@RequestBody @Valid CreateInquiryReq request) {
        // TODO 토큰 받아오기
        csService.createInquiry(10L, request);
        return ResponseCustom.OK();
    }



}