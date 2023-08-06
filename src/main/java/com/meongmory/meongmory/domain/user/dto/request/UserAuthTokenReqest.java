package com.meongmory.meongmory.domain.user.dto.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserAuthTokenReqest {
    private Long userId;
    private String nickname;
}
