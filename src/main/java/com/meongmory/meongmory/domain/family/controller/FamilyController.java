package com.meongmory.meongmory.domain.family.controller;

import com.meongmory.meongmory.domain.family.dto.request.CreateFamilyReq;
import com.meongmory.meongmory.domain.family.service.FamilyService;
import com.meongmory.meongmory.global.response.ResponseCustom;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@Api(tags = "FAMILY API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/families")
public class FamilyController {
    private final FamilyService familyService;

    @PostMapping("")
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(S0001)다이어리 생성 성공", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
            @ApiResponse(responseCode = "400", description = "(G0004)잘못된 요청\n (F0003)사용자당 가족 생성은 최대 1개", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
            @ApiResponse(responseCode = "404", description = "(U0001)존재하지 않는 유저", content = @Content(schema = @Schema(implementation = ResponseCustom.class)))
    })
    @Operation(summary = "펫 다이어리 생성", description = "이름을 입력받아 펫 다이어리(가족)을 생성합니다.")
    public ResponseCustom createFamily(@RequestBody @Valid CreateFamilyReq createFamilyReq){
        familyService.createFamily(createFamilyReq, 1L);
        return ResponseCustom.OK();
    }
}
