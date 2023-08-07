package com.meongmory.meongmory.domain.diary.repository;

import com.meongmory.meongmory.domain.diary.entity.DiaryFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryFileRepository extends JpaRepository<DiaryFile, Long> {
}
