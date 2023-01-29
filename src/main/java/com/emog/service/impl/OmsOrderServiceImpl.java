//package com.emog.service.impl;
//
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import com.emog.dao.OmsOrderOperateHistoryDao;
//import com.emog.dto.OmsOrderDeliveryParam;
//import com.emog.mapper.OmsOrderMapper;
//import com.emog.model.OmsOrderExample;
//import com.emog.model.OmsOrderOperateHistory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.emog.dao.OmsOrderDao;
//import com.emog.dto.OmsOrderQueryParam;
//import com.emog.mapper.OmsOrderMapper;
//import com.emog.model.OmsOrder;
//import com.emog.model.OmsOrderExample;
//import com.emog.service.OmsOrderService;
//import com.github.pagehelper.PageHelper;
//
//@Service
//public class OmsOrderServiceImpl implements OmsOrderService {
//
//	@Autowired
//    private OmsOrderMapper orderMapper;
//	@Autowired
//	private OmsOrderDao orderDao;
//	@Autowired
//	OmsOrderOperateHistoryDao orderOperateHistoryDao
//
//	@Override
//	public List<OmsOrder> list(OmsOrderQueryParam queryParam, Integer pageSize, Integer pageNum) {
//		PageHelper.startPage(pageNum, pageSize);
//		return orderDao.getList(queryParam);
//	}
//
//	@Override
//	public int delete(List<Long> ids) {
//		OmsOrder record = new OmsOrder();
//		record.setDeleteStatus(1);
//		OmsOrderExample example = new OmsOrderExample();
//		example.createCriteria().andDeleteStatusEqualTo(0).andIdIn(ids);
//		return orderMapper.updateByExampleSelective(record, example);
//	}
//
//	@Override
//	public int updateNote(Long id, String note) {
//		OmsOrder record = new OmsOrder();
//		record.setId(id);
//		record.setNote(note);
//		record.setModifyTime(new Date());
//		int count = orderMapper.updateByPrimaryKeySelective(record);
////		OmsOrderOperateHistory history = new OmsOrderOperateHistory();
//		return 0;
//	}
//
//	@Override
//	public int delivery(List<OmsOrderDeliveryParam> deliveryParamList) {
//		//批量发货
//		int count = orderDao.delivery(deliveryParamList);
//		//添加操作记录
//		List<OmsOrderOperateHistory> operateHistoryList = deliveryParamList.stream()
//				.map(omsOrderDeliveryParam -> {
//					OmsOrderOperateHistory history = new OmsOrderOperateHistory();
//					history.setOrderId(omsOrderDeliveryParam.getOrderId());
//					history.setCreateTime(new Date());
//					history.setOperateMan("后台管理员");
//					history.setOrderStatus(2);
//					history.setNote("完成发货");
//					return history;
//				}).collect(Collectors.toList());
//		orderOperateHistoryDao.insertList(operateHistoryList);
//		return count;
//	}
//
//}
