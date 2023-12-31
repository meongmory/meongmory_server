package com.meongmory.meongmory.domain.diary.entity;

import lombok.Getter;

@Getter
public enum Reason {
    JUNK("홍보/도배"),
    PORN("음란물/유해한 정보"),
    POOR_CONTENT("내용이 부실함"),
    NOT_FIT("게시글 성격에 부적합함");

    private final String reason;

     Reason(String reason) {
        this.reason = reason;
    }

}
