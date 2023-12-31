package com.meongmory.meongmory.domain.diary.service;

import com.meongmory.meongmory.domain.diary.dto.request.RecordDiaryReq;
import com.meongmory.meongmory.domain.diary.dto.response.DetailDiaryRes;
import com.meongmory.meongmory.domain.diary.dto.response.GetDiariesRes;
import com.meongmory.meongmory.domain.diary.dto.response.RecordCommentReq;
import com.meongmory.meongmory.domain.diary.entity.*;
import com.meongmory.meongmory.domain.diary.repository.DiaryCommentRepository;
import com.meongmory.meongmory.domain.diary.repository.DiaryFileRepository;
import com.meongmory.meongmory.domain.diary.repository.DiaryPetRepository;
import com.meongmory.meongmory.domain.diary.repository.DiaryRepository;
import com.meongmory.meongmory.domain.family.entity.Family;
import com.meongmory.meongmory.domain.family.entity.FamilyMember;
import com.meongmory.meongmory.domain.family.entity.MemberType;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiaryService {

  private final DiaryRepository diaryRepository;
  private final DiaryCommentRepository diaryCommentRepository;
  private final FamilyRepository familyRepository;
  private final UserRepository userRepository;
  private final FamilyMemberRepository familyMemberRepository;
  private final PetRepository petRepository;
  private final DiaryPetRepository diaryPetRepository;
  private final DiaryFileRepository diaryFileRepository;

  public GetDiariesRes getDiaries(Long userId, Long familyId, String sortType)
  {
    User user = userRepository.findByUserIdAndIsEnable(userId, true).orElseThrow(() -> new BaseException(BaseResponseCode.USER_NOT_FOUND));
    Family family = familyRepository.findByFamilyIdAndIsEnable(familyId, true).orElseThrow(() -> new BaseException(BaseResponseCode.FAMILY_NOT_FOUND));
    return GetDiariesRes.toDto(family, SortType.getSortTypeByName(sortType));
  }

  public GetDiariesRes getDiariesByPet(Long userId, Long petId, String sortType)
  {
    User user = userRepository.findByUserIdAndIsEnable(userId, true).orElseThrow(() -> new BaseException(BaseResponseCode.USER_NOT_FOUND));
    Pet pet = petRepository.findByPetIdAndIsEnable(petId, true).orElseThrow(() -> new BaseException(BaseResponseCode.PET_NOT_FOUND));
    FamilyMember familyMember = familyMemberRepository.findByFamilyAndUserAndIsEnable(pet.getFamily(), user, true).orElseThrow(() -> new BaseException(BaseResponseCode.FAMILY_MEMBER_NOT_FOUND));

    List<DiaryPet> diaryPet = diaryPetRepository.findByPetAndIsEnable(pet, true);
    List<Diary> diaries = diaryPet.stream().map(m -> diaryRepository.findByDiaryIdAndIsEnable(m.getDiary().getDiaryId(), true).orElseThrow(() -> new BaseException(BaseResponseCode.DIARY_NOT_FOUND))).collect(Collectors.toList());
    return GetDiariesRes.toDto(filteredDiariesByScope(diaries, familyMember.getType()), pet, SortType.getSortTypeByName(sortType));
  }

  public DetailDiaryRes detailDiary(Long userId, Long diaryId)
  {
    User user = userRepository.findByUserIdAndIsEnable(userId, true).orElseThrow(() -> new BaseException(BaseResponseCode.USER_NOT_FOUND));
    Diary diary = diaryRepository.findByDiaryIdAndIsEnable(diaryId, true).orElseThrow(() -> new BaseException(BaseResponseCode.DIARY_NOT_FOUND));
    FamilyMember familyMember = familyMemberRepository.findByFamilyAndUserAndIsEnable(diary.getFamily(), user, true).orElseThrow(() -> new BaseException(BaseResponseCode.FAMILY_MEMBER_NOT_FOUND));

    List<DiaryComment> comments = diaryCommentRepository.findByDiaryAndIsEnable(diary, true);
    if(!checkScope(diary.getScope(), familyMember.getType())) throw new BaseException(BaseResponseCode.INVALID_SCOPE);

    return DetailDiaryRes.toDto(diary, diary.getFamily().getPets(), comments);
  }

  @Transactional
  public Long recordDiary(Long userId, RecordDiaryReq recordDiaryReq)
  {
    User user = userRepository.findByUserIdAndIsEnable(userId, true).orElseThrow(() -> new BaseException(BaseResponseCode.USER_NOT_FOUND));
    List<Pet> pets = recordDiaryReq.getPets().stream().map(m -> petRepository.findByPetIdAndIsEnable(m.getPetId(), true).orElseThrow(() -> new BaseException(BaseResponseCode.PET_NOT_FOUND))).collect(Collectors.toList());
    Family family = familyRepository.findByFamilyIdAndIsEnable(recordDiaryReq.getFamilyId(), true).orElseThrow(() -> new BaseException(BaseResponseCode.FAMILY_NOT_FOUND));
    Diary diary = diaryRepository.save(Diary.toEntity(family, recordDiaryReq.getTitle(), recordDiaryReq.getContent(), Scope.getScopeByName(recordDiaryReq.getScope())));
    // DiaryPet 연관관계 설정
    pets.forEach(pet -> diaryPetRepository.save(DiaryPet.toEntity(diary, pet)));
    // DiaryFile 연관관계 설정
    recordDiaryReq.getFiles().stream().map(f -> DiaryFile.toEntity(diary, f.getFileKey(), FileType.getFileTypeByName(f.getFileType()))).forEach(diaryFileRepository::save);
    return diary.getDiaryId();
  }

  @Transactional
  public Long recordComment(Long userId, Long diaryId, RecordCommentReq comment)
  {
    User user = userRepository.findByUserIdAndIsEnable(userId, true).orElseThrow(() -> new BaseException(BaseResponseCode.USER_NOT_FOUND));
    Diary diary = diaryRepository.findByDiaryIdAndIsEnable(diaryId, true).orElseThrow(() -> new BaseException(BaseResponseCode.DIARY_NOT_FOUND));
    familyMemberRepository.findByFamilyAndUserAndIsEnable(diary.getFamily(), user, true).orElseThrow(() -> new BaseException(BaseResponseCode.FAMILY_MEMBER_NOT_FOUND));
    return diaryCommentRepository.save(DiaryComment.toEntity(diary, user, comment.getComment())).getDiaryCommentId();
  }

  public List<Diary> filteredDiariesByScope(List<Diary> diaries, MemberType type)
  {
    List<Diary> inScopeDiaries = new ArrayList<>();
    for (Diary diary : diaries) if(checkScope(diary.getScope(), type)) inScopeDiaries.add(diary);
    return inScopeDiaries;
  }

  public boolean checkScope(Scope scope, MemberType type)
  {
    if(type == MemberType.OWNER) return true;
    return scope.name().split("_")[1].equals(type.name());
  }
}
