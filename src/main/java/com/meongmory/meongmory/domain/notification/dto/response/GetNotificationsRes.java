package com.meongmory.meongmory.domain.notification.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@Schema(description = "알림 목록 조회 DTO")
public class GetNotificationsRes {

    @Schema(description = "알림 목록들")
    private List<NotificationDetail> notificationList;

}
