package com.emog.service;

import com.emog.model.UmsAdmin;

public interface UmsAdminCacheService {

    /**
     * 获取缓存后台用户信息
     */
    UmsAdmin getAdmin(String username);

    /**
     * 设置缓存后台用户信息
     */
    void setAdmin(UmsAdmin admin);
}
