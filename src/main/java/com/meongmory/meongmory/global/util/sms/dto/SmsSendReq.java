package com.meongmory.meongmory.global.util.sms.dto;

import com.meongmory.meongmory.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsSendReq {
    //아이디 중복확인을 위한 객체
    @Schema(type = "String", description = "전화번호", example = "010-0000-0000")
    @Size(min = 11, max = 13, message = "SM0004")
    @NotBlank(message = "SM0001")
    private String phone;

    public static SmsSendReq send(User user) {
        return SmsSendReq.builder()
                .phone(user.getPhone())
                .build();
    }

}
