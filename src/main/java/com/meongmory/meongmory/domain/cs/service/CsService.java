package com.meongmory.meongmory.domain.cs.service;

import com.meongmory.meongmory.domain.cs.dto.assembler.CsAssembler;

import com.meongmory.meongmory.domain.cs.dto.response.GetNoticeDetailRes;
import com.meongmory.meongmory.domain.cs.entity.Notice;
import com.meongmory.meongmory.domain.cs.repository.NoticeRepository;
import com.meongmory.meongmory.global.exception.BaseException;
import com.meongmory.meongmory.global.exception.BaseResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.meongmory.meongmory.domain.cs.dto.response.GetNoticesRes;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CsService {

    private final NoticeRepository noticeRepository;
    private final CsAssembler csAssembler;


    public GetNoticeDetailRes getNoticeDetail(Long noticeId) {
        Notice notice = noticeRepository.findByNoticeIdAndIsEnable(noticeId, true).orElseThrow(() -> new BaseException(BaseResponseCode.INVALID_NOTICE_ID));
        return csAssembler.toGetNoticeDetailResDto(notice);
    }
}

    public GetNoticesRes getNotices() {
        List<Notice> noticeList = noticeRepository.findAllByIsEnableOrderByCreatedAt(true);
        return csAssembler.toGetNoticesResDto(noticeList);
    }
}

