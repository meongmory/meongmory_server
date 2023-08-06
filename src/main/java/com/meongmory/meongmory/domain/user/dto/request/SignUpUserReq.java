package com.meongmory.meongmory.domain.user.dto.request;

import com.meongmory.meongmory.domain.user.entity.License;
import com.meongmory.meongmory.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class SignUpUserReq {
    @NotNull
    @Schema(type = "boolean", example = "true / false", description = "마켓팅 수신 동의 여부")
    private Boolean marketing;
    @NotNull(message="닉네임을 입력해주세요.")
    @Schema(type = "string", example = "이승학", description = "닉네임")
    private String nickname;
    @NotNull(message="번호를 입력해주세요.")
    @Schema(type = "string", example = "010-4158-8124", description = "번호")
    private String phone;
    @Schema(type = "string", example = "PRO", description = "버전")
    private License license=License.GENERAL;

    public User toEntity(SignUpUserReq signUpUserReq){
        return User.builder()
                .nickname(signUpUserReq.getNickname())
                .phone(signUpUserReq.getNickname())
                .marketing(signUpUserReq.getMarketing())
                .license(signUpUserReq.getLicense())
                .build();
    }








}
