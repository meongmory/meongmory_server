package com.meongmory.meongmory.domain.cs.dto.response;

import com.meongmory.meongmory.domain.cs.entity.Notice;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

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

    public static NoticeRes toDto(Notice notice) {
        return NoticeRes.builder()
                .noticeId(notice.getNoticeId())
                .title(notice.getTitle())
                .date(notice.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .build();
    }

}
