package com.meongmory.meongmory.domain.cs.dto.assembler;

import com.meongmory.meongmory.domain.cs.dto.request.CreateInquiryReq;
import com.meongmory.meongmory.domain.cs.entity.Inquiry;
import com.meongmory.meongmory.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.meongmory.meongmory.domain.cs.dto.response.GetNoticeDetailRes;
import com.meongmory.meongmory.domain.cs.dto.response.GetNoticesRes;
import com.meongmory.meongmory.domain.cs.dto.response.NoticeRes;
import com.meongmory.meongmory.domain.cs.entity.Notice;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class CsAssembler {

    public Inquiry toInquiry(User user, CreateInquiryReq request) {
        return Inquiry.builder()
                .user(user)
                .email(request.getEmail())
                .content(request.getContent())
                .build();
    }

    public GetNoticeDetailRes toGetNoticeDetailResDto(Notice notice) {
        return GetNoticeDetailRes.builder()
                .title(notice.getTitle())
                .comment(notice.getComment())
                .date(notice.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .build();
    }

    public GetNoticesRes toGetNoticesResDto(List<Notice> noticeList) {
        return GetNoticesRes.builder()
                .noticeList(noticeList.stream()
                        .map(this::toNoticeRes)
                        .collect(Collectors.toList()))
                .build();
    }

    private NoticeRes toNoticeRes(Notice notice) {
        return NoticeRes.builder()
                .noticeId(notice.getNoticeId())
                .title(notice.getTitle())
                .date(notice.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .build();
    }
}
