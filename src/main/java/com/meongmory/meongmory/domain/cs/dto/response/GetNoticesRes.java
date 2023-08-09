package com.meongmory.meongmory.domain.cs.dto.response;

import com.meongmory.meongmory.domain.cs.entity.Notice;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@AllArgsConstructor
@Schema(description = "공지사항 목록 조회 DTO")
public class GetNoticesRes {

    @Schema(description = "공지사항 목록들")
    private List<NoticeRes> noticeList;

    public static GetNoticesRes toDto(List<Notice> noticeList) {
        return GetNoticesRes.builder()
                .noticeList(noticeList.stream()
                        .map(NoticeRes::toDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
