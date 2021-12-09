package com.emog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.emog.dto.OmsOrderQueryParam;
import com.emog.model.OmsOrder;

@Mapper
public interface OmsOrderDao {
    /**
     * 条件查询订单
     */
    List<OmsOrder> getList(@Param("queryParam") OmsOrderQueryParam queryParam);

    
}

