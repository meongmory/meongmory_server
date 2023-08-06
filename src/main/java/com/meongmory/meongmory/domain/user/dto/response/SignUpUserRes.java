package com.meongmory.meongmory.domain.user.dto.response;

import com.meongmory.meongmory.domain.user.entity.License;
import com.meongmory.meongmory.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignUpUserRes {
    private Long userId;
    private String nickname;
    private Boolean marketing;
    private License license;
    private String imageURL;
    private String accessToken;

    public static SignUpUserRes toDto(User user, String accessToken){
        SignUpUserRes signUpUserRes=new SignUpUserRes();
        signUpUserRes.userId=user.getUserId();
        signUpUserRes.nickname=user.getNickname();
        signUpUserRes.marketing=user.getMarketing();
        signUpUserRes.imageURL=user.getImage();
        signUpUserRes.license=user.getLicense();
        signUpUserRes.accessToken=accessToken;
        return signUpUserRes;
    }

}
