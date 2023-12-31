package com.meongmory.meongmory.domain.family.controller;

import com.meongmory.meongmory.domain.family.dto.request.CreateFamilyPetReq;
import com.meongmory.meongmory.domain.family.dto.request.CreateFamilyReq;
import com.meongmory.meongmory.domain.family.dto.request.InviteFamilyMemberReq;
import com.meongmory.meongmory.domain.family.dto.response.AnimalTypeListRes;
import com.meongmory.meongmory.domain.family.dto.response.FamilyInviteCodeRes;
import com.meongmory.meongmory.domain.family.dto.response.FamilyListRes;
import com.meongmory.meongmory.domain.family.service.FamilyService;
import com.meongmory.meongmory.global.resolver.Auth;
import com.meongmory.meongmory.global.resolver.IsLogin;
import com.meongmory.meongmory.global.resolver.LoginStatus;
import com.meongmory.meongmory.global.response.ResponseCustom;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "FAMILY API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/families")
public class FamilyController {
    private final FamilyService familyService;

    @Auth
    @PostMapping("")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(S0001)다이어리 생성 성공", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
            @ApiResponse(responseCode = "400", description = "(G0004)잘못된 요청\n (F0003)사용자당 가족 생성은 최대 1개", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
            @ApiResponse(responseCode = "404", description = "(U0001)존재하지 않는 유저", content = @Content(schema = @Schema(implementation = ResponseCustom.class)))
    })
    @Operation(summary = "펫 다이어리 생성", description = "이름을 입력받아 펫 다이어리(가족)을 생성합니다.")
    public ResponseCustom createFamily(
            @Parameter(description = "JWT 토큰 헤더") @IsLogin LoginStatus loginStatus,
            @RequestBody @Valid CreateFamilyReq createFamilyReq){
        familyService.createFamily(createFamilyReq, loginStatus.getUserId());
        return ResponseCustom.OK();
    }

    @GetMapping("/pet/kind")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(S0001)반려동물 품종 검색 성공", content = @Content(schema = @Schema(implementation = AnimalTypeListRes.class))),
            @ApiResponse(responseCode = "400", description = "(A0001)반려동물 종류 이름 오류", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
    })
    @Operation(summary = "반려동물 품종 검색", description = "검색어와 반려동물 종류를 받아 품종을 검색합니다.")
    public ResponseCustom<AnimalTypeListRes> getAnimalType(
            @Parameter(description = "반려동물 품종 검색어", example = "말티즈") @RequestParam(required = false) String searchword,
            @Parameter(description = "반려동물 종류", example = "강아지") @RequestParam(required = false) String type,
            @Parameter Pageable pageable){
        return ResponseCustom.OK(familyService.getAnimalType(searchword, type, pageable));
    }

    @Auth
    @GetMapping("/{familyId}/invite")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(S0001)펫 다이어리(가족) 초대 코드 조회 성공", content = @Content(schema = @Schema(implementation = FamilyInviteCodeRes.class))),
            @ApiResponse(responseCode = "400", description = "(F0004)펫 다이어리 생성 유저만 접근 가능", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
            @ApiResponse(responseCode = "404", description = "(U0001)존재하지 않는 유저\n (F0001)존재하지 않은 다이어리(가족)\n (F0002)존재하지 않은 가족 구성원", content = @Content(schema = @Schema(implementation = ResponseCustom.class)))
    })
    @Operation(summary = "펫 다이어리 초대 코드 조회", description = "펫 다이어리(가족)을 초대할 초대 코드 정보를 확인합니다.")
    public ResponseCustom<FamilyInviteCodeRes> getFamilyInviteCode(
            @Parameter(description = "JWT 토큰 헤더") @IsLogin LoginStatus loginStatus,
            @Parameter(description = "(Long) 펫 다이어리(가족) Id", example = "1") @PathVariable(value = "familyId") Long familyId){
        return ResponseCustom.OK(familyService.getFamilyInviteCode(familyId, loginStatus.getUserId()));
    }

    @Auth
    @PostMapping("/{familyId}/pet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(S0001)펫 다이어리(가족) 반려동물 생성 성공", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
            @ApiResponse(responseCode = "400", description = "(F0004)펫 다이어리 생성 유저/가족만 접근 가능", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
            @ApiResponse(responseCode = "404", description = "(U0001)존재하지 않는 유저\n (F0001)존재하지 않은 다이어리(가족)\n (F0002)존재하지 않은 가족 구성원\n (A0002)존재하지 않는 반려동물 품종\n (P0002)존재하지 않는 반려동물 성별 타입", content = @Content(schema = @Schema(implementation = ResponseCustom.class)))
    })
    @Operation(summary = "펫 다이어리 반려동물 생성", description = "펫 다이어리(가족) 내 반려동물을 생성합니다.")
    public ResponseCustom postFamilyPet(
            @Parameter(description = "JWT 토큰 헤더") @IsLogin LoginStatus loginStatus,
            @Parameter(description = "(Long) 펫 다이어리(가족) Id", example = "1") @PathVariable(value = "familyId") Long familyId,
            @RequestBody @Valid CreateFamilyPetReq createFamilyPetReq){
        familyService.postFamilyPet(createFamilyPetReq, familyId, loginStatus.getUserId());
        return ResponseCustom.OK();
    }

    @Auth
    @DeleteMapping("/{familyId}/pet/{petId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(S0001)펫 다이어리(가족) 반려동물 삭제 성공", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
            @ApiResponse(responseCode = "400", description = "(F0004)펫 다이어리 생성 유저/가족만 접근 가능", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
            @ApiResponse(responseCode = "404", description = "(U0001)존재하지 않는 유저\n (F0001)존재하지 않은 다이어리(가족)\n (F0002)존재하지 않은 가족 구성원\n (A0002)존재하지 않는 반려동물 품종\n (P0002)존재하지 않는 반려동물 성별 타입\n (P0001)존재하지 않는 반려동물", content = @Content(schema = @Schema(implementation = ResponseCustom.class)))
    })
    @Operation(summary = "펫 다이어리 반려동물 삭제", description = "펫 다이어리(가족) 내 반려동물을 삭제합니다.")
    public ResponseCustom deleteFamilyPet(
            @Parameter(description = "JWT 토큰 헤더") @IsLogin LoginStatus loginStatus,
            @Parameter(description = "(Long) 펫 다이어리(가족) Id", example = "1") @PathVariable(value = "familyId") Long familyId,
            @Parameter(description = "(Long) 반려동물 Id", example = "1") @PathVariable(value = "petId") Long petId
    ){
        familyService.deleteFamilyPet(familyId, petId, loginStatus.getUserId());
        return ResponseCustom.OK();
    }

    @Auth
    @GetMapping("/{familyId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(S0001)가족/친구/반려동물 리스트 불러오기 성공", content = @Content(schema = @Schema(implementation = FamilyListRes.class))),
            @ApiResponse(responseCode = "404", description = "(U0001)존재하지 않는 유저\n (F0001)존재하지 않은 다이어리(가족)\n (F0002)존재하지 않은 가족 구성원\n", content = @Content(schema = @Schema(implementation = ResponseCustom.class)))
    })
    @Operation(summary = "가족/친구/반려동물 리스트", description = "가족 내 반려동물, 가족, 친구 리스트를 불러온다. ")
    public ResponseCustom<FamilyListRes> getFamilyList(
            @Parameter(description = "JWT 토큰 헤더") @IsLogin LoginStatus loginStatus,
            @Parameter(description = "(Long) 펫 다이어리(가족) Id", example = "1") @PathVariable(value = "familyId") Long familyId){
        return ResponseCustom.OK(familyService.getFamilyAndPetList(familyId, loginStatus.getUserId()));
    }

    @Auth
    @DeleteMapping("/{familyId}/{userId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(S0001)가족/친구/반려동물 리스트 불러오기 성공", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
            @ApiResponse(responseCode = "400", description = "(F0004)펫 다이어리 생성 유저만 접근 가능", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
            @ApiResponse(responseCode = "404", description = "(U0001)존재하지 않는 유저\n (F0001)존재하지 않은 다이어리(가족)\n (F0002)존재하지 않은 가족 구성원\n", content = @Content(schema = @Schema(implementation = ResponseCustom.class)))
    })
    @Operation(summary = "펫 다이어리 가족, 친구 삭제", description = "펫 다이어리(가족)에서 가족, 친구를 삭제한다. ")
    public ResponseCustom deleteFamilyMember(
            @Parameter(description = "JWT 토큰 헤더") @IsLogin LoginStatus loginStatus,
            @Parameter(description = "(Long) 펫 다이어리(가족) Id", example = "1") @PathVariable(value = "familyId") Long familyId,
            @Parameter(description = "(Long) 사용자 Id", example = "1") @PathVariable(value = "userId") Long userId
    ){
        familyService.deleteFamilyMember(familyId, userId, loginStatus.getUserId());
        return ResponseCustom.OK();
    }

    @PostMapping("/invite")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(S0001)가족/친구 초대 성공", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
            @ApiResponse(responseCode = "404", description = "(U0001)존재하지 않는 유저\n (F0001)존재하지 않은 다이어리(가족)\n (F0007)이미 가족 구성원인 유저\n", content = @Content(schema = @Schema(implementation = ResponseCustom.class)))
    })
    @Operation(summary = "펫 다이어리 가족, 친구 초대", description = "펫 다이어리(가족)에서 가족, 친구를 초대한다. ")
    public ResponseCustom inviteFamilyMember(
            @Parameter(description = "JWT 토큰 헤더") @IsLogin LoginStatus loginStatus,
            @RequestBody InviteFamilyMemberReq familyMemberReq){
        familyService.inviteFamilyMember(familyMemberReq, loginStatus.getUserId());
        return ResponseCustom.OK();
    }


}
