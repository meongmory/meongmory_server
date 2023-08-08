package com.meongmory.meongmory.global.util.redis;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class RedisTemplateServiceImpl implements RedisTemplateService {

    private final RedisTemplate<String, String> redisTemplate;

    public void deleteUserRefreshToken(String userId){
        if(redisTemplate.opsForValue().get(userId)!=null) redisTemplate.delete(userId);
    }

    @Transactional(readOnly = true)
    public String getUserRefreshToken(@NotNull String userId) {
        return redisTemplate.opsForValue().get(userId);
    }

    public void setUserRefreshToken(@NotNull String userId, String refreshToken) {
        redisTemplate.opsForValue().set(userId, refreshToken);
    }

    public void setCertificationCode(String phone, String code) {
        // 1분 만료시간 설정
        redisTemplate.opsForValue().set(phone, code, 60, TimeUnit.SECONDS);
    }

    public String getCertificationCode(String phone) {
        return redisTemplate.opsForValue().get(phone);
    }


}
