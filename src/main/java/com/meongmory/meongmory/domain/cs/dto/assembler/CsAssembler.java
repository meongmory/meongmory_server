package com.meongmory.meongmory.domain.cs.dto.assembler;

import com.meongmory.meongmory.domain.cs.dto.response.GetNoticeDetailRes;
import com.meongmory.meongmory.domain.cs.entity.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class CsAssembler {
    public GetNoticeDetailRes toGetNoticeDetailResDto(Notice notice) {
        return GetNoticeDetailRes.builder()
                .title(notice.getTitle())
                .comment(notice.getComment())
                .date(notice.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .build();

    }
}
