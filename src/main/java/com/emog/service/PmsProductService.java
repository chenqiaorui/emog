package com.emog.service;

import com.emog.dto.PmsProductParam;
import com.emog.model.PmsProduct;

import java.util.List;

public interface PmsProductService {
    /**
     * 创建商品
     */
    int create(PmsProductParam productParam);

    /**
     * 更新商品
     */
    int update(Long id, PmsProductParam productParam);

    /**
     * 分页查询商品
     */
    List<PmsProduct> list(Integer pageSize, Integer pageNum);

    /**
     * 批量更新删除状态
     */
    int updateDeleteStatus(List<Long> ids, Integer deleteStatus);

    /**
     * 根据商品名称模糊查询
     */
    List<PmsProduct> list(String keyword);
}
