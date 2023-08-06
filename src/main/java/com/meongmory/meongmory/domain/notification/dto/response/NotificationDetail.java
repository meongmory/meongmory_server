package com.meongmory.meongmory.domain.notification.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class NotificationDetail {

    @Schema(description = "알림 제목")
    private String title;

    @Schema(description = "알림 내용")
    private String content;

    @Schema(description = "알림 날짜")
    private String date;
}
