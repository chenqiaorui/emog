package com.emog.service;

import java.util.List;

import com.emog.dto.OmsOrderQueryParam;
import com.emog.model.OmsOrder;
import com.emog.model.SmsHomeBrand;

public interface SmsHomeBrandService {
    /**
     * 分页查询推荐品牌
     */
    List<SmsHomeBrand> list(Integer pageSize, Integer pageNum);


}