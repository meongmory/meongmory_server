package com.meongmory.meongmory.domain.diary.service;

import com.meongmory.meongmory.domain.diary.dto.assembler.DiaryAssembler;
import com.meongmory.meongmory.domain.diary.dto.request.RecordDiaryReq;
import com.meongmory.meongmory.domain.diary.dto.response.DetailDiaryRes;
import com.meongmory.meongmory.domain.diary.dto.response.GetDiariesRes;
import com.meongmory.meongmory.domain.diary.entity.*;
import com.meongmory.meongmory.domain.diary.repository.DiaryCommentRepository;
import com.meongmory.meongmory.domain.diary.repository.DiaryFileRepository;
import com.meongmory.meongmory.domain.diary.repository.DiaryPetRepository;
import com.meongmory.meongmory.domain.diary.repository.DiaryRepository;
import com.meongmory.meongmory.domain.family.entity.Family;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {

  private final DiaryRepository diaryRepository;
  private final DiaryCommentRepository diaryCommentRepository;
  private final FamilyRepository familyRepository;
  private final UserRepository userRepository;
  private final FamilyMemberRepository familyMemberRepository;
  private final PetRepository petRepository;
  private final DiaryPetRepository diaryPetRepository;
  private final DiaryFileRepository diaryFileRepository;
  private final DiaryAssembler diaryAssembler;

  @Override
  public GetDiariesRes getDiaries(Long userId, Long petId, String sortType)
  {
//    User user = userRepository.findByUserIdAndIsEnable(userId, true).orElseThrow(() -> new BaseException(BaseResponseCode.USER_NOT_FOUND));
//    Pet pet = petRepository.findByPetIdAndIsEnable(petId, true).orElseThrow(() -> new BaseException(BaseResponseCode.PET_NOT_FOUND));
//    FamilyMember familyMember = familyMemberRepository.findByFamilyAndUserAndIsEnable(pet.getFamily(), user, true).orElseThrow(() -> new BaseException(BaseResponseCode.FAMILY_MEMBER_NOT_FOUND));
//    List<Diary> inScopeDiaries = diaryAssembler.filteredDiariesByScope(diaryRepository.findByPetAndIsEnable(pet, true), familyMember.getType());
//    return GetDiariesRes.toDto(inScopeDiaries, SortType.getSortTypeByName(sortType));
    return null;
  }

  @Override
  public DetailDiaryRes detailDiary(Long userId, Long diaryId)
  {
//    User user = userRepository.findByUserIdAndIsEnable(userId, true).orElseThrow(() -> new BaseException(BaseResponseCode.USER_NOT_FOUND));
//    Diary diary = diaryRepository.findByDiaryIdAndIsEnable(diaryId, true).orElseThrow(() -> new BaseException(BaseResponseCode.DIARY_NOT_FOUND));
//    Pet pet = petRepository.findByPetIdAndIsEnable(diary.getPet().getPetId(), true).orElseThrow(() -> new BaseException(BaseResponseCode.PET_NOT_FOUND));
//    FamilyMember familyMember = familyMemberRepository.findByFamilyAndUserAndIsEnable(pet.getFamily(), user, true).orElseThrow(() -> new BaseException(BaseResponseCode.FAMILY_MEMBER_NOT_FOUND));
//    List<DiaryComment> comments = diaryCommentRepository.findByDiaryAndIsEnable(diary, true);
//
//    if(!diaryAssembler.checkScope(diary.getScope(), familyMember.getType())) throw new BaseException(BaseResponseCode.INVALID_SCOPE);
//    return DetailDiaryRes.toDto(diary, pet, comments);
    return null;
  }

  @Override
  public Long recordDiary(Long userId, RecordDiaryReq recordDiaryReq)
  {
    User user = userRepository.findByUserIdAndIsEnable(userId, true).orElseThrow(() -> new BaseException(BaseResponseCode.USER_NOT_FOUND));
    List<Pet> pets = recordDiaryReq.getPets().stream().map(m -> petRepository.findByPetIdAndIsEnable(m.getPetId(), true).orElseThrow(() -> new BaseException(BaseResponseCode.PET_NOT_FOUND))).collect(Collectors.toList());
    Family family = familyRepository.findByFamilyIdAndIsEnable(recordDiaryReq.getFamilyId(), true).orElseThrow(() -> new BaseException(BaseResponseCode.FAMILY_NOT_FOUND));

    Diary diary = diaryRepository.save(Diary.toEntity(family, recordDiaryReq.getTitle(), recordDiaryReq.getContent(), Scope.getScopeByName(recordDiaryReq.getScope())));
    pets.forEach(pet -> diaryPetRepository.save(DiaryPet.toEntity(diary, pet)));

    recordDiaryReq.getFiles().stream().map(f -> DiaryFile.toEntity(diary, f.getFileKey(), FileType.getFileTypeByName(f.getFileType()))).forEach(diaryFileRepository::save);
    return diary.getDiaryId();
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
