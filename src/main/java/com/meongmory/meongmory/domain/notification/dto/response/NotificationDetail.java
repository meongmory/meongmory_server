package com.meongmory.meongmory.domain.notification.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class NotificationDetail {

    @Schema(type = "String", description = "알림 제목", example = "댓글")
    private String title;

    @Schema(type = "String", description = "알림 내용", example = "소징님이 고영어 모음집 글에 새로운 댓글을 남겼습니다.")
    private String content;

    @Schema(type = "String", description = "알림 날짜", example = "2023-09-01")
    private String date;
}
