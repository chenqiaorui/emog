package com.emog.service;

import java.util.List;

import com.emog.dto.OmsOrderQueryParam;
import com.emog.model.OmsOrder;

public interface OmsOrderService {
    /**
     * 订单查询
     */
    List<OmsOrder> list(OmsOrderQueryParam queryParam, Integer pageSize, Integer pageNum);
}