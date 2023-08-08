package com.meongmory.meongmory.global.util.sms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmsSendRes {
    //문자인증값 반환을 위한 객체
    private String value;
    private String message;
}
