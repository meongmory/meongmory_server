package com.meongmory.meongmory.domain.diary.service;

import com.meongmory.meongmory.domain.diary.dto.request.RecordDiaryReq;
import com.meongmory.meongmory.domain.diary.dto.response.DetailDiaryRes;
import com.meongmory.meongmory.domain.diary.dto.response.GetDiariesRes;
import com.meongmory.meongmory.domain.diary.repository.DiaryRepository;
import com.meongmory.meongmory.domain.family.repository.FamilyPetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {

  private final DiaryRepository diaryRepository;

  @Override
  public GetDiariesRes getDiaries(Long userId, Long familyId)
  {

    return null;
  }

  @Override
  public DetailDiaryRes detailDiary(Long userId, Long diaryId)
  {
    return null;
  }

  @Override
  public Long recordDiary(RecordDiaryReq recordDiaryReq)
  {
    return null;
  }

  @Override
  public Long recordComment(Long userId, Long diaryId, String comment)
  {
    return null;
  }

  @Override
  public Long deleteComment(Long userId, Long diaryId, Long diaryCommentId) {
    return null;
  }
}
