package com.meongmory.meongmory.domain.cs.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@Schema(description = "공지사항 목록 조회 DTO")
public class GetNoticesRes {

    @Schema(description = "공지사항 목록들")
    private List<NoticeRes> noticeList;

}
