package com.meongmory.meongmory.domain.cs.service;

import com.meongmory.meongmory.domain.cs.dto.assembler.CsAssembler;
import com.meongmory.meongmory.domain.cs.entity.Notice;
import com.meongmory.meongmory.domain.cs.repository.NoticeRepository;
import com.meongmory.meongmory.domain.cs.dto.response.GetNoticesRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CsService {

    private final NoticeRepository noticeRepository;
    private final CsAssembler csAssembler;

    public GetNoticesRes getNotices() {
        List<Notice> noticeList = noticeRepository.findAllByIsEnableOrderByCreatedAt(true);
        return csAssembler.toGetNoticesResDto(noticeList);
    }
}
