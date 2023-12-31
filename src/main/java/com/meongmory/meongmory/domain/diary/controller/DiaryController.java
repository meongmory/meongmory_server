package com.meongmory.meongmory.domain.diary.controller;

import com.meongmory.meongmory.domain.diary.dto.request.RecordDiaryReq;
import com.meongmory.meongmory.domain.diary.dto.response.DetailDiaryRes;
import com.meongmory.meongmory.domain.diary.dto.response.GetDiariesRes;
import com.meongmory.meongmory.domain.diary.dto.response.RecordCommentReq;
import com.meongmory.meongmory.domain.diary.service.DiaryService;
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
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "diary", description = "DIARY API")
@Api(tags = "DIARY API")
@RestController
@RequestMapping(value = "/diaries")
@RequiredArgsConstructor
public class DiaryController {

  private final DiaryService diaryService;

  @Operation(summary = "다이어리 전체 조회", description = "familyId에 해당하는 가족의 모든 다이어리를 조회한다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "(S0001) 조회 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseCustom.class))}),
          @ApiResponse(responseCode = "404", description = "(U0001) 존재하지 않는 유저\n (P0001) 존재하지 않는 가족\n (D0001) 존재하지 않는 sortType", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseCustom.class))}),
  })
  @Auth
  @ResponseBody
  @GetMapping("/{familyId}")
  public ResponseCustom<GetDiariesRes> getDiaries(
          @Parameter(description = "JWT 토큰 헤더") @IsLogin LoginStatus loginStatus,
          @Parameter(description = "가족 id") @PathVariable(name = "familyId") Long familyId,
          @Parameter(description = "정렬 형식 ('갤러리형식' or '리스트형식')") @RequestParam String sortType
  )
  {
    return ResponseCustom.OK(diaryService.getDiaries(loginStatus.getUserId(), familyId, sortType));
  }



  @Operation(summary = "반려동물 다이어리 조회", description = "petId에 해당하는 반려동물의 다이어리를 조회한다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "(S0001) 조회 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseCustom.class))}),
          @ApiResponse(responseCode = "404", description = "(U0001) 존재하지 않는 유저\n (P0001) 존재하지 않는 반려동물\n (F0002) 존재하지 않는 가족 구성원\n (D0002) 존재하지 않는 다이어리\n (D0001) 존재하지 않는 sortType", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseCustom.class))}),
  })
  @Auth
  @ResponseBody
  @GetMapping("/read/{petId}")
  public ResponseCustom<GetDiariesRes> getDiariesByPet(
          @Parameter(description = "JWT 토큰 헤더") @IsLogin LoginStatus loginStatus,
          @Parameter(description = "반려동물 id") @PathVariable(name = "petId") Long petId,
          @Parameter(description = "정렬 형식 ('갤러리형식' or '리스트형식')") @RequestParam String sortType
  )
  {
    return ResponseCustom.OK(diaryService.getDiariesByPet(loginStatus.getUserId(), petId, sortType));
  }

  @Operation(summary = "다이어리 상세 조회", description = "다이어리를 상세 조회한다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "(S0001) 조회 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseCustom.class))}),
          @ApiResponse(responseCode = "404", description = "(U0001) 존재하지 않는 유저\n (D0002) 존재하지 않는 다이어리\n (F0002) 존재하지 않는 가족 구성원\n", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseCustom.class))}),
          @ApiResponse(responseCode = "403", description = "(D0003) 유효하지 않은 다이어리 접근 권한", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseCustom.class))}),
  })
  @Auth
  @ResponseBody
  @GetMapping("/detail/{diaryId}")
  public ResponseCustom<DetailDiaryRes> detailDiary(
          @Parameter(description = "다이어리 id") @PathVariable("diaryId") Long diaryId,
          @Parameter(description = "JWT 토큰 헤더") @IsLogin LoginStatus loginStatus
  )
  {
    return ResponseCustom.OK(diaryService.detailDiary(loginStatus.getUserId(), diaryId));
  }

  @Operation(summary = "다이어리 등록", description = "다이어리를 등록한다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "(S0001) 다이어리 등록 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseCustom.class))}),
          @ApiResponse(responseCode = "404", description = "(U0001) 존재하지 않는 유저\n (P0001) 존재하지 않는 반려동물\n (F0001) 존재하지 않는 가족\n (U0001) 존재하지 않는 다이어리 scope\n (D0001) 존재하지 않는 fileType", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseCustom.class))}),
  })
  @Auth
  @ResponseBody
  @PostMapping("")
  public ResponseCustom<Long> recordDiary(
          @Parameter(description = "JWT 토큰 헤더") @IsLogin LoginStatus loginStatus,
          @Valid @RequestBody RecordDiaryReq recordDiaryReq
  )
  {
    return ResponseCustom.OK(diaryService.recordDiary(loginStatus.getUserId(), recordDiaryReq));
  }

  @Operation(summary = "다이어리 댓글 등록", description = "다이어리에 댓글을 등록한다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "등록 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseCustom.class))}),
          @ApiResponse(responseCode = "404", description = "(U0001) 존재하지 않는 유저\n (D0002) 존재하지 않는 다이어리", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseCustom.class))}),
  })
  @Auth
  @ResponseBody
  @PostMapping("/{diaryId}/comment")
  public ResponseCustom<Long> recordComment(
          @PathVariable("diaryId") Long diaryId,
          @Parameter(description = "JWT 토큰 헤더") @IsLogin LoginStatus loginStatus,
          @Valid @RequestBody RecordCommentReq comment
  ) {

    return ResponseCustom.OK(diaryService.recordComment(loginStatus.getUserId(), diaryId, comment));
  }
}
