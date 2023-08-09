package com.meongmory.meongmory.domain.user.service;

import com.meongmory.meongmory.domain.user.dto.request.ModifyMyPageReq;
import com.meongmory.meongmory.domain.user.dto.request.SignInUserReq;
import com.meongmory.meongmory.domain.user.dto.request.SignUpUserReq;
import com.meongmory.meongmory.domain.user.dto.response.MyPageRes;
import com.meongmory.meongmory.domain.user.dto.response.SignUpUserRes;
import com.meongmory.meongmory.domain.user.entity.User;
import com.meongmory.meongmory.domain.user.repository.UserRepository;
import com.meongmory.meongmory.global.exception.BaseException;
import com.meongmory.meongmory.global.exception.BaseResponseCode;
import com.meongmory.meongmory.global.util.redis.RedisTemplateService;
import com.meongmory.meongmory.global.util.token.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final TokenUtil tokenUtils;
    private final RedisTemplateService redisTemplateService;
    @Transactional
    public SignUpUserRes signUp(SignUpUserReq signUpUserReq) {
        if(userRepository.findByPhone(signUpUserReq.getPhone()).isPresent()){
            throw new BaseException(BaseResponseCode.ALREADY_PHONENUM);}
        User user=userRepository.save(signUpUserReq.toEntity(signUpUserReq));
        return SignUpUserRes.toDto(user,saveToken(user));
    }
    @Transactional
    public SignUpUserRes signIn(SignInUserReq signInUserReq) {
        User user=userRepository.findByPhone(signInUserReq.getPhone()).orElseThrow(() -> new BaseException(BaseResponseCode.USER_NUMBER_NOT_FONUND));
        if(user!=null){
            user.login();
        }
        return SignUpUserRes.toDto(user,saveToken(user));
    }

    private String saveToken(User user) {
        String accessToken = tokenUtils.createAccessToken(user.getUserId(), user.getNickname());
        String refreshToken = tokenUtils.createRefreshToken(user.getUserId(), user.getNickname()); // 레디스
        tokenUtils.getUserIdFromFullToken(accessToken);
        redisTemplateService.setUserRefreshToken(user.getUserId().toString(), refreshToken);
        return accessToken;
    }

    public MyPageRes getMyPage(Long userId) {
        User user=userRepository.findByUserId(userId).orElseThrow(()->new BaseException(BaseResponseCode.USER_NOT_FOUND));
        return MyPageRes.toDto(user);
    }

    @Transactional
    public void modifyMyPage(Long userId, ModifyMyPageReq modifyMyPageReq) {
        User user=userRepository.findByUserId(userId).orElseThrow(()->new BaseException(BaseResponseCode.USER_NOT_FOUND));
        if(StringUtils.hasText(modifyMyPageReq.getNickname()))user.modifyMyPageNickName(modifyMyPageReq.getNickname());

    }
    @Transactional
    public void deleteUser(Long userId) {
        User user=userRepository.findByUserId(userId).orElseThrow(()->new BaseException(BaseResponseCode.USER_NOT_FOUND));
        user.deleteUser();
        redisTemplateService.deleteUserRefreshToken(userId.toString());
    }
    @Transactional
    public void logout(Long userId) {
        User user=userRepository.findByUserId(userId).orElseThrow(()->new BaseException(BaseResponseCode.USER_NOT_FOUND));
        user.logout();
        redisTemplateService.deleteUserRefreshToken(userId.toString());
    }
}
