package com.meongmory.meongmory.domain.diary.repository;

import com.meongmory.meongmory.domain.diary.entity.DiaryPet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryPetRepository extends JpaRepository<DiaryPet, Long> {
}
