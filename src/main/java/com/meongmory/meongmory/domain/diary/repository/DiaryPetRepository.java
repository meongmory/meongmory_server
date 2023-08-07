package com.meongmory.meongmory.domain.diary.repository;

import com.meongmory.meongmory.domain.diary.entity.DiaryPet;
import com.meongmory.meongmory.domain.family.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiaryPetRepository extends JpaRepository<DiaryPet, Long> {
  List<DiaryPet> findByPetAndIsEnable(Pet pet, Boolean isEnable);
}
