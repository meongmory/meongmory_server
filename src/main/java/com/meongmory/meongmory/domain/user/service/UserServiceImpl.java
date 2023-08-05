package com.meongmory.meongmory.domain.user.service;

import com.meongmory.meongmory.domain.user.dto.request.SignUpUserReq;
import com.meongmory.meongmory.domain.user.dto.response.SignUpUserRes;
import com.meongmory.meongmory.domain.user.entity.User;
import com.meongmory.meongmory.domain.user.repository.UserRepository;
import com.meongmory.meongmory.global.exception.BaseException;
import com.meongmory.meongmory.global.exception.BaseResponseCode;
import com.meongmory.meongmory.global.util.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserServiceImpl {
    private final UserRepository userRepository;
    private final TokenUtil tokenUtils;
    @Transactional
    public SignUpUserRes signUp(SignUpUserReq signUpUserReq) {
        if(userRepository.findByPhone(signUpUserReq.getPhone()).isPresent()){
            throw new BaseException(BaseResponseCode.ALREADY_PHONENUM);}
        User user=userRepository.save(signUpUserReq.toEntity());
        return SignUpUserRes.toDto(user,SaveToken(user));
    }

    private String SaveToken(User user) {
        String accessToken = tokenUtils.createAccessToken(user.getUserId(), user.getNickname());
        tokenUtils.getUserIdFromFullToken(accessToken);
        return accessToken;
    }
}
