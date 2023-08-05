package com.meongmory.meongmory.domain.diary.controller;

import com.meongmory.meongmory.domain.diary.dto.request.RecordDiaryReq;
import com.meongmory.meongmory.domain.diary.dto.response.DetailDiaryRes;
import com.meongmory.meongmory.domain.diary.dto.response.GetDiariesRes;
import com.meongmory.meongmory.domain.diary.service.DiaryService;
import com.meongmory.meongmory.global.response.ResponseCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diaries")
public class DiaryController {

  private final DiaryService diaryService;

  @ResponseBody
  @GetMapping("/{familyId}")
  public ResponseCustom<GetDiariesRes> getDiaries(
          @PathVariable("familyId") Long familyId,
          @RequestParam Long userId
  )
  {
    return ResponseCustom.OK(diaryService.getDiaries(userId, familyId));
  }

  @ResponseBody
  @GetMapping("/{diaryId}")
  public ResponseCustom<DetailDiaryRes> detailDiary(
          @PathVariable("diaryId") Long diaryId,
          @RequestParam Long userId
  )
  {
    return ResponseCustom.OK(diaryService.detailDiary(userId, diaryId));
  }

  @PostMapping("")
  public ResponseCustom<Long> recordDiary(
          RecordDiaryReq recordDiaryReq
  )
  {
    return ResponseCustom.OK(diaryService.recordDiary(recordDiaryReq));
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
