package com.meongmory.meongmory.domain.user.dto.response;

import com.meongmory.meongmory.domain.user.entity.License;
import com.meongmory.meongmory.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignUpUserRes {
    private String accessToken;

    public static SignUpUserRes toDto(String token){
        SignUpUserRes signUpUserRes=new SignUpUserRes();
        signUpUserRes.accessToken=token;
        return signUpUserRes;
    }

}
