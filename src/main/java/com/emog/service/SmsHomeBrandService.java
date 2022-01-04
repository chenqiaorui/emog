package com.emog.service;

import java.util.List;

import com.emog.model.SmsHomeBrand;

public interface SmsHomeBrandService {
    /**
     * 分页查询推荐品牌
     */
    List<SmsHomeBrand> list(Integer pageSize, Integer pageNum);

    /*批量删除推荐品牌*/
	int delete(List<Long> ids);

	/*添加推荐品牌*/
	int create(List<SmsHomeBrand> homeBrandList);


}