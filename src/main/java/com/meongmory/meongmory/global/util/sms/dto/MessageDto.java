package com.meongmory.meongmory.global.util.sms.dto;

import com.sun.xml.bind.v2.TODO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    //네이버에서 원하는 메시지를 담기 위한 객체
    private String to;
    private String content;
}
