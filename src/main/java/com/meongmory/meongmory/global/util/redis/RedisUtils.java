package com.meongmory.meongmory.global.util.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisUtils {
  private final RedisTemplate<String, String> redisTemplate;
  private final ObjectMapper objectMapper;

  public <T> boolean set(String key, T data, int minutes) {
    try {
      String value = objectMapper.writeValueAsString(data);
      redisTemplate.opsForValue().set(key, value);
      return true;
    } catch(Exception e){
      log.error("error: {}", e);
      return false;
    }
  }

  public <T> Optional<T> get(String key, Class<T> classType) {
    String value = redisTemplate.opsForValue().get(key);

    if(value == null){
      return Optional.empty();
    }
    try {
      return Optional.of(objectMapper.readValue(value, classType));
    } catch(Exception e){
      log.error("error: {}", e);
      return Optional.empty();
    }
  }

  public void delete(String key) {
    redisTemplate.opsForValue().getAndDelete(key);
  }

}
