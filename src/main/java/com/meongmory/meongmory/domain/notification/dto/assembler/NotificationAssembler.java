package com.meongmory.meongmory.domain.notification.dto.assembler;

import com.meongmory.meongmory.domain.notification.entity.Notification;
import com.meongmory.meongmory.domain.notification.dto.response.GetNotificationsRes;
import com.meongmory.meongmory.domain.notification.dto.response.NotificationDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NotificationAssembler {
    public GetNotificationsRes toGetNotificationsDto(List<Notification> notificationList) {
        return GetNotificationsRes.builder()
                .notificationList(notificationList.stream().map(this::toNotificationDetailDto).collect(Collectors.toList())).build();
    }

    private NotificationDetail toNotificationDetailDto(Notification notification) {
        return NotificationDetail.builder()
                .title(notification.getType().getName())
                .content(notification.getContent())
                .date(notification.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .build();
    }
}
