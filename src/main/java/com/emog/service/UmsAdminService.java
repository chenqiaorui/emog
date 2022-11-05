package com.emog.service;

import com.emog.dto.PmsProductParam;
import com.emog.dto.UmsAdminParam;
import com.emog.model.PmsProduct;
import com.emog.model.UmsAdmin;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UmsAdminService {
    /**
     * 注册用户
     */
    UmsAdmin register(UmsAdminParam adminParam);

    /**
     * 登录
     * */

    String login(String username, String password);

    /**
     * 根据用户名获取后台管理员
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 获取缓存服务
     */
    UmsAdminCacheService getCacheService();

}
