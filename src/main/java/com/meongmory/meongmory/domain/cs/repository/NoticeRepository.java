package com.meongmory.meongmory.domain.cs.repository;

import com.meongmory.meongmory.domain.cs.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findAllByIsEnableOrderByCreatedAt(boolean isEnable);
}
