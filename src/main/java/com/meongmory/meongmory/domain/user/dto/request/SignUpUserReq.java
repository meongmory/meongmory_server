package com.meongmory.meongmory.domain.user.dto.request;

import com.meongmory.meongmory.domain.user.entity.License;
import com.meongmory.meongmory.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
public class SignUpUserReq {
    @Schema(type = "boolean", example = "true / false", description = "마켓팅 수신 동의 여부")
    private Boolean marketing;
    @Schema(type = "string", example = "이승학", description = "닉네임")
    private String nickname;
    @Schema(type = "string", example = "010-41588124", description = "번호")
    private String phone;
    @Schema(type = "string", example = "PRO", description = "버전")
    private License license;

    public User toEntity(){
        return User.builder()
                .nickname(this.nickname)
                .phone(this.phone)
                .marketing(this.marketing)
                .license(this.license)
                .build();
    }








}
