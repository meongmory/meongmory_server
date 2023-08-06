package com.meongmory.meongmory.domain.notification.repository;

import com.meongmory.meongmory.domain.notification.entity.Notification;
import com.meongmory.meongmory.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserAndIsEnableOrderByCreatedAtDesc(User user, boolean isEnable);
}
