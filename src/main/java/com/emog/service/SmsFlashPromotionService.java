package com.emog.service;

import java.util.List;

import com.emog.dto.OmsOrderQueryParam;
import com.emog.model.OmsOrder;

public interface SmsFlashPromotionService {
    /**
     * 修改上下线状态
     */
    int updateStatus(Long id, Integer status);


}