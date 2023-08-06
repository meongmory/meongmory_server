package com.meongmory.meongmory.domain.diary.repository;

import com.meongmory.meongmory.domain.diary.entity.Diary;
import com.meongmory.meongmory.domain.family.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

  List<Diary> findByPetAndIsEnable(Pet pet, Boolean isEnable);

  Optional<Diary> findByDiaryIdAndIsEnable(Long diaryId, Boolean isEnable);
}

