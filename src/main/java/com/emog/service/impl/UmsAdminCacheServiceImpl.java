package com.emog.service.impl;

import com.emog.common.RedisService;
import com.emog.model.UmsAdmin;
import com.emog.service.UmsAdminCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UmsAdminCacheServiceImpl implements UmsAdminCacheService {
    @Value("${spring.redis.database}")
    private String REDIS_DATABASE;
    @Value("${spring.redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${spring.redis.key.admin}")
    private String REDIS_KEY_ADMIN;

    @Autowired
    RedisService redisService;


    @Override
    public UmsAdmin getAdmin(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + username;
        return (UmsAdmin) redisService.get(key);
    }

    @Override
    public void setAdmin(UmsAdmin admin) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getUsername();
        redisService.set(key, admin, REDIS_EXPIRE);
    }
}
