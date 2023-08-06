package com.meongmory.meongmory.domain.cs.dto.assembler;

import com.meongmory.meongmory.domain.cs.dto.request.CreateInquiryReq;
import com.meongmory.meongmory.domain.cs.entity.Inquiry;
import com.meongmory.meongmory.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
}
