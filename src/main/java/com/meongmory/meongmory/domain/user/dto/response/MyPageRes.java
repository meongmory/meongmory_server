package com.meongmory.meongmory.domain.user.dto.response;

import com.meongmory.meongmory.domain.user.entity.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MyPageRes {
    private Long userIdx;
    private String nickname;
    private String myPageImgUrl;
    private String phone;

    public static MyPageRes toDto(User user){
        MyPageRes myPageRes=new MyPageRes();
        myPageRes.userIdx=user.getUserId();
        myPageRes.nickname=user.getNickname();
        myPageRes.myPageImgUrl=user.getImage();
        myPageRes.phone=user.getPhone();

        return myPageRes;
    }

}
