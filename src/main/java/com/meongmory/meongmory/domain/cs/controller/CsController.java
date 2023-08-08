package com.meongmory.meongmory.domain.cs.controller;


import com.meongmory.meongmory.domain.cs.dto.request.CreateInquiryReq;
import com.meongmory.meongmory.domain.cs.dto.response.GetNoticeDetailRes;
import com.meongmory.meongmory.domain.cs.service.CsService;
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

import io.swagger.v3.oas.annotations.Parameter;
import com.meongmory.meongmory.domain.cs.dto.response.GetNoticesRes;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Api(tags = "CS API")
@RestController
@RequestMapping(value = "/cs")
@RequiredArgsConstructor
public class CsController {

    private final CsService csService;


    @PostMapping("/inquires")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(S0001)문의하기 성공", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
            @ApiResponse(responseCode = "400", description = "(G0002)이메일 미입력\n (G0003)올바르지 않은 이메일 형식\n (G0004)내용 미입력", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
            @ApiResponse(responseCode = "404", description = "(U0001)존재하지 않는 유저\n", content = @Content(schema = @Schema(implementation = ResponseCustom.class)))
    })
    @Operation(summary = "문의하기", description = "이메일 및 내용을 입력 받아 문의사항을 저장합니다.")
    public ResponseCustom createInquiry(@RequestBody @Valid CreateInquiryReq request) {
        // TODO 토큰 받아오기
        csService.createInquiry(1L, request);
        return ResponseCustom.OK();
    }

    @GetMapping("/notices/{noticeId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(S0001)공지사항 상세 조회 성공", content = @Content(schema = @Schema(implementation = GetNoticeDetailRes.class))),
            @ApiResponse(responseCode = "400", description = "(C0001)존재하지 않는 공지사항", content = @Content(schema = @Schema(implementation = ResponseCustom.class)))
    })
    @Operation(summary = "공지사항 상세 조회", description = "공지사항 Id를 이용해 상세 내용을 조회합니다.")
    public ResponseCustom<GetNoticeDetailRes> getNoticeDetail(@Parameter(description = "공지사항 Id", example = "1") @PathVariable Long noticeId) {
        return ResponseCustom.OK(csService.getNoticeDetail(noticeId));
    }

    @GetMapping("/notices")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(S0001)공지사항 목록 조회 성공", content = @Content(schema = @Schema(implementation = GetNoticesRes.class)))
    })
    @Operation(summary = "공지사항 목록 조회", description = "공지사항 전체 목록을 조회합니다.")
    public ResponseCustom<GetNoticesRes> getNotices() {
        return ResponseCustom.OK(csService.getNotices());
    }

}

