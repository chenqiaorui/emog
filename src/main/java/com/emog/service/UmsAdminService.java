package com.emog.service;

import com.emog.dto.PmsProductParam;
import com.emog.dto.UmsAdminParam;
import com.emog.model.PmsProduct;
import com.emog.model.UmsAdmin;

import java.util.List;

public interface UmsAdminService {
    /**
     * 注册用户
     */
    UmsAdmin register(UmsAdminParam adminParam);

}
