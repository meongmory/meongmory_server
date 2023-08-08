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

    @Schema(type = "String", description = "공지사항 제목", example = "당신도 될 수 있습니다. 애니모리 런칭 기념 100억 이벤트!")
    private String title;

    @Schema(type = "String", description = "공지사항 내용", example = "멍모리 런칭 기념 사장님이 100억 주십니다!")
    private String comment;

    @Schema(type = "String", description = "공지사항 날짜", example = "2023-08-23")
    private String date;

    public static GetNoticeDetailRes toDto(Notice notice) {
        return GetNoticeDetailRes.builder()
                .title(notice.getTitle())
                .comment(notice.getComment())
                .date(notice.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .build();
    }
}
