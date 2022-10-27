package com.emog.service;

import com.emog.dto.PmsProductParam;

public interface PmsProductService {
    /**
     * 创建商品
     */
    int create(PmsProductParam productParam);

    /**
     * 更新商品
     */
    int update(Long id, PmsProductParam productParam);
}
