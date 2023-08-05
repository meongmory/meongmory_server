package com.meongmory.meongmory.domain.diary.service;

import com.meongmory.meongmory.domain.diary.dto.assembler.DiaryAssembler;
import com.meongmory.meongmory.domain.diary.dto.request.RecordDiaryReq;
import com.meongmory.meongmory.domain.diary.dto.response.DetailDiaryRes;
import com.meongmory.meongmory.domain.diary.dto.response.GetDiariesRes;
import com.meongmory.meongmory.domain.diary.entity.Diary;
import com.meongmory.meongmory.domain.diary.entity.SortType;
import com.meongmory.meongmory.domain.diary.repository.DiaryRepository;
import com.meongmory.meongmory.domain.family.entity.Family;
import com.meongmory.meongmory.domain.family.entity.FamilyMember;
import com.meongmory.meongmory.domain.family.entity.Pet;
import com.meongmory.meongmory.domain.family.repository.FamilyMemberRepository;
import com.meongmory.meongmory.domain.family.repository.FamilyRepository;
import com.meongmory.meongmory.domain.family.repository.PetRepository;
import com.meongmory.meongmory.domain.user.entity.User;
import com.meongmory.meongmory.domain.user.repository.UserRepository;
import com.meongmory.meongmory.global.exception.BaseException;
import com.meongmory.meongmory.global.exception.BaseResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {

  private final DiaryRepository diaryRepository;
  private final FamilyRepository familyRepository;
  private final UserRepository userRepository;
  private final FamilyMemberRepository familyMemberRepository;
  private final PetRepository petRepository;
  private final DiaryAssembler diaryAssembler;

  @Override
  public GetDiariesRes getDiaries(Long userId, Long familyId, Long petId, String sortType)
  {
    User user = userRepository.findByUserIdAndIsEnable(userId, true).orElseThrow(() -> new BaseException(BaseResponseCode.USER_NOT_FOUND));
    Family family = familyRepository.findByFamilyIdAndIsEnable(familyId, true).orElseThrow(() -> new BaseException(BaseResponseCode.FAMILY_NOT_FOUND));
    FamilyMember familyMember = familyMemberRepository.findByFamilyAndUserAndIsEnable(family, user, true).orElseThrow(() -> new BaseException(BaseResponseCode.FAMILY_MEMBER_NOT_FOUND));
    Pet pet = petRepository.findByPetIdAndIsEnable(petId, true).orElseThrow(() -> new BaseException(BaseResponseCode.PET_NOT_FOUND));
    List<Diary> inScopeDiaries = diaryAssembler.filterDiariesByMemberType(diaryRepository.findByPetAndIsEnable(pet, true), familyMember.getType());
    return GetDiariesRes.toDto(inScopeDiaries, SortType.getSortTypeByName(sortType));
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
