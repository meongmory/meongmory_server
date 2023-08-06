package com.meongmory.meongmory.domain.cs.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class NoticeRes {

    @Schema(description = "공지사항 id")
    private Long noticeId;

    @Schema(description = "공지사항 제목")
    private String title;

    @Schema(description = "공지사항 날짜")
    private String date;

}
