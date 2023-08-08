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
@Schema(description = "공지사항 상세 조회 DTO")
public class GetNoticeDetailRes {

    @Schema(description = "공지사항 제목")
    private String title;

    @Schema(description = "공지사항 내용")
    private String comment;

    @Schema(description = "공지사항 날짜")
    private String date;

    public static GetNoticeDetailRes toDto(Notice notice) {
        return GetNoticeDetailRes.builder()
                .title(notice.getTitle())
                .comment(notice.getComment())
                .date(notice.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .build();
    }
}
