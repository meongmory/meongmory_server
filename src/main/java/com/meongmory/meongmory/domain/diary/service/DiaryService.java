package com.meongmory.meongmory.domain.diary.service;

import com.meongmory.meongmory.domain.diary.dto.request.RecordDiaryReq;
import com.meongmory.meongmory.domain.diary.dto.response.DetailDiaryRes;
import com.meongmory.meongmory.domain.diary.dto.response.GetDiariesRes;

public interface DiaryService {
  GetDiariesRes getDiaries(Long userId, Long petId, String sortType);

  DetailDiaryRes detailDiary(Long userId, Long diaryId);

  Long recordDiary(Long userId, RecordDiaryReq recordDiaryReq);

  Long recordComment( Long userId, Long diaryId, String comment);

  Long deleteComment(Long userId, Long diaryId, Long diaryCommentId);
}
