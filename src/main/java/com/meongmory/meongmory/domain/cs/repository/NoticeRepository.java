package com.meongmory.meongmory.domain.cs.repository;

import com.meongmory.meongmory.domain.cs.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    Optional<Notice> findByNoticeIdAndIsEnable(Long noticeId, boolean isEnable);
    List<Notice> findAllByIsEnableOrderByCreatedAt(boolean isEnable);

}



