package com.meongmory.meongmory.global.util.redis;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@RequiredArgsConstructor
@Service
public class RedisServiceMock implements RedisTemplateService {

    private final HashMap<String, String> redisTemplate;

    public void deleteUserRefreshToken(String userId){
//        if(redisTemplate.opsForValue().get(userIdx)!=null) redisTemplate.delete(userIdx);
        redisTemplate.remove(userId);
    }

    @Transactional(readOnly = true)
    public String getUserRefreshToken(@NotNull String userId) {
//        return redisTemplate.opsForValue().get(userIdx);
        return redisTemplate.get(userId);
    }

    public void setUserRefreshToken(@NotNull String userId, String refreshToken) {
//        redisTemplate.opsForValue().set(userIdx, refreshToken);
        redisTemplate.put(userId, refreshToken);
    }
}
