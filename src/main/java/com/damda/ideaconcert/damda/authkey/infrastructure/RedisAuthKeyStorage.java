package com.damda.ideaconcert.damda.authkey.infrastructure;

import java.time.ZonedDateTime;
import java.util.Date;

import com.damda.ideaconcert.damda.authkey.application.AuthKeyStorage;
import com.damda.ideaconcert.damda.authkey.application.AuthKeyStorageRequest;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RedisAuthKeyStorage implements AuthKeyStorage{
    private final StringRedisTemplate redisTemplate;

    @Override
    public void create(AuthKeyStorageRequest request) {
        String key = request.getType() + "-" + request.getUserId();
        Date EXPIRED_TIME = Date.from(ZonedDateTime.now().plusMinutes(5).toInstant());
        redisTemplate.opsForValue().set(key, request.getAuthKey());
        redisTemplate.expireAt(key, EXPIRED_TIME); 
    }

    @Override
    public String read(AuthKeyStorageRequest request) {
        String key = request.getType() + "-" + request.getUserId();
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(AuthKeyStorageRequest request) {
        String key = request.getType() + "-" + request.getUserId();
        redisTemplate.delete(key);
    }
    
}
