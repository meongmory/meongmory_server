package com.meongmory.meongmory.domain.cs.controller;

import com.meongmory.meongmory.domain.cs.service.CsService;
import com.meongmory.meongmory.domain.cs.dto.response.GetNoticesRes;
import com.meongmory.meongmory.global.response.ResponseCustom;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "cs", description = "CS API")
@RestController
@RequestMapping(value = "/cs")
@RequiredArgsConstructor
public class CsController {

    private final CsService csService;

    @GetMapping("/notices")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "공지사항 목록 조회 성공", content = @Content(schema = @Schema(implementation = GetNoticesRes.class)))
    })
    @Operation(summary = "공지사항 목록 조회", description = "공지사항 전체 목록을 조회합니다.")
    public ResponseCustom<GetNoticesRes> getNotices() {
        return ResponseCustom.OK(csService.getNotices());
    }
}
