package com.meongmory.meongmory.domain.cs.controller;


import com.meongmory.meongmory.domain.cs.dto.response.GetNoticeDetailRes;
import com.meongmory.meongmory.domain.cs.service.CsService;
import com.meongmory.meongmory.global.exception.BaseRes;
import com.meongmory.meongmory.global.response.ResponseCustom;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import com.meongmory.meongmory.domain.cs.dto.response.GetNoticesRes;
import io.swagger.annotations.Api;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "CS API")
@RestController
@RequestMapping(value = "/cs")
@RequiredArgsConstructor
public class CsController {

    private final CsService csService;


    @GetMapping("/notices/{noticeId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "공지사항 상세 조회 성공", content = @Content(schema = @Schema(implementation = GetNoticeDetailRes.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 공지사항", content = @Content(schema = @Schema(implementation = BaseRes.class)))
    })

    @Operation(summary = "공지사항 상세 조회", description = "공지사항 Id를 이용해 상세 내용을 조회합니다.")
    public ResponseCustom<GetNoticeDetailRes> getNoticeDetail(@Parameter(description = "공지사항 Id") @PathVariable Long noticeId) {
        return ResponseCustom.OK(csService.getNoticeDetail(noticeId));
    }


    @GetMapping("/notices")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "공지사항 목록 조회 성공", content = @Content(schema = @Schema(implementation = GetNoticesRes.class)))
    })
    @Operation(summary = "공지사항 목록 조회", description = "공지사항 전체 목록을 조회합니다.")
    public ResponseCustom<GetNoticesRes> getNotices() {
        return ResponseCustom.OK(csService.getNotices());
    }

}
