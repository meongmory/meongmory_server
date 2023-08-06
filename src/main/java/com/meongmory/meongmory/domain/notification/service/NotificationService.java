package com.meongmory.meongmory.domain.notification.service;

import com.meongmory.meongmory.domain.notification.entity.Notification;
import com.meongmory.meongmory.domain.notification.dto.assembler.NotificationAssembler;
import com.meongmory.meongmory.domain.notification.dto.response.GetNotificationsRes;
import com.meongmory.meongmory.domain.notification.repository.NotificationRepository;
import com.meongmory.meongmory.domain.user.entity.User;
import com.meongmory.meongmory.domain.user.repository.UserRepository;
import com.meongmory.meongmory.global.exception.BaseException;
import com.meongmory.meongmory.global.exception.BaseResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    private final NotificationAssembler notificationAssembler;

    public GetNotificationsRes getNotifications(Long userId) {
        User user = userRepository.findByUserIdAndIsEnable(userId, true).orElseThrow(() -> new BaseException(BaseResponseCode.NOT_FOUND_USER));
        List<Notification> notificationList = notificationRepository.findByUserAndIsEnableOrderByCreatedAtDesc(user, true);
        return notificationAssembler.toGetNotificationsDto(notificationList);
    }
}
