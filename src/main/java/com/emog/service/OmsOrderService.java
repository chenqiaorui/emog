//package com.emog.service;
//
//import java.util.List;
//
//import com.emog.dto.OmsOrderDeliveryParam;
//import com.emog.dto.OmsOrderQueryParam;
//import com.emog.model.OmsOrder;
//
//public interface OmsOrderService {
//    /**
//     * 订单查询
//     */
//    List<OmsOrder> list(OmsOrderQueryParam queryParam, Integer pageSize, Integer pageNum);
//
//    /**批量删除订单*/
//    int delete(List<Long> ids);
//
//    /*修改订单备注*/
//    int updateNote(Long id, String note);
//
//    /**批量发货*/
//    int delivery(List<OmsOrderDeliveryParam> deliveryParamList);
//}