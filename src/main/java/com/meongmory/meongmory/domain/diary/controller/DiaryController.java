package com.meongmory.meongmory.domain.diary.controller;

import com.meongmory.meongmory.domain.diary.dto.request.RecordDiaryReq;
import com.meongmory.meongmory.domain.diary.dto.response.DetailDiaryRes;
import com.meongmory.meongmory.domain.diary.dto.response.GetDiariesRes;
import com.meongmory.meongmory.domain.diary.service.DiaryService;
import com.meongmory.meongmory.global.exception.BaseException;
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

@Api(tags = "DIARY API")
@RestController
@RequestMapping(value = "/diaries")
@RequiredArgsConstructor
public class DiaryController {

  private final DiaryService diaryService;

  @Operation(summary = "다이어리 조회", description = "다이어리를 조회한다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "조회 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GetDiariesRes.class))}),
  })
  @ResponseBody
  @GetMapping("/{petId}")
  public ResponseCustom<GetDiariesRes> getDiaries(
          @Parameter(description = "유저 id") @RequestParam Long userId,
          @Parameter(description = "반려동물 id") @PathVariable(name = "petId") Long petId,
          @Parameter(description = "정렬 형식 ('갤러리형식' or '리스트형식')") @RequestParam String sortType
  )
  {
    return ResponseCustom.OK(diaryService.getDiaries(userId, petId, sortType));
  }

  @Operation(summary = "다이어리 상세 조회", description = "다이어리를 상세 조회한다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "조회 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DetailDiaryRes.class))}),
  })
  @ResponseBody
  @GetMapping("/details/{diaryId}")
  public ResponseCustom<DetailDiaryRes> detailDiary(
          @Parameter(description = "다이어리 id") @PathVariable("diaryId") Long diaryId,
          @Parameter(description = "유저 id") @RequestParam Long userId
  )
  {
    return ResponseCustom.OK(diaryService.detailDiary(userId, diaryId));
  }

  @Operation(summary = "다이어리 등록", description = "다이어리를 등록한다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "(S0001) 다이어리 등록 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseCustom.class))}),
          @ApiResponse(responseCode = "404", description = "(U0001) 존재하지 않는 유저\n (P0001) 존재하지 않는 반려동물\n (F0001) 존재하지 않는 가족\n (U0001) 존재하지 않는 다이어리 scope\n (D0001) 존재하지 않는 fileType", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseCustom.class))}),
  })
  @ResponseBody
  @PostMapping("")
  public ResponseCustom<Long> recordDiary(
          @Parameter(description = "유저 id") @RequestParam Long userId,
          @RequestBody RecordDiaryReq recordDiaryReq
  )
  {
    return ResponseCustom.OK(diaryService.recordDiary(userId, recordDiaryReq));
  }

  @PostMapping("/{diaryId}/comment")
  public ResponseCustom<Long> recordComment(
          @PathVariable("diaryId") Long diaryId,
          @RequestParam Long userId,
          @RequestParam String comment
  )
  {
    return ResponseCustom.OK(diaryService.recordComment(userId, diaryId, comment));
  }

  @DeleteMapping("/{diaryId}/comment")
  public ResponseCustom<Long> deleteComment(
          @PathVariable("diaryId") Long diaryId,
          @RequestParam Long userId,
          @RequestParam Long diaryCommentId
  )
  {
    return ResponseCustom.OK(diaryService.deleteComment(userId, diaryId, diaryCommentId));
  }
}
