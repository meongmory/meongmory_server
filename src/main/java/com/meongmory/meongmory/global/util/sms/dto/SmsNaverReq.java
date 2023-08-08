package com.meongmory.meongmory.global.util.sms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsNaverReq {
    //네이버에서 원하는 정보를 담기 위한 객체
    private String type;
    private String from;
    private String Content;
    private List<MessageDto> messages;
}
