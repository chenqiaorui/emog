package com.emog.service.impl;

import java.util.Date;
import java.util.List;

import com.emog.mapper.OmsOrderMapper;
import com.emog.model.OmsOrderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emog.dao.OmsOrderDao;
import com.emog.dto.OmsOrderQueryParam;
import com.emog.model.OmsOrder;
import com.emog.service.OmsOrderService;
import com.github.pagehelper.PageHelper;

@Service
public class OmsOrderServiceImpl implements OmsOrderService {
	
	@Autowired
	private OmsOrderDao orderDao;

	@Autowired
	private OmsOrderMapper orderMapper;

	@Override
	public List<OmsOrder> list(OmsOrderQueryParam queryParam, Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		return orderDao.getList(queryParam);
	}

	@Override
	public int delete(List<Long> ids) {
		OmsOrder record = new OmsOrder();
		record.setDeleteStatus(1);
		OmsOrderExample example = new OmsOrderExample();
		example.createCriteria().andDeleteStatusEqualTo(0).andIdIn(ids);
		return orderMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateNote(Long id, String note) {
		OmsOrder record = new OmsOrder();
		record.setId(id);
		record.setNote(note);
		record.setModifyTime(new Date());
		int count = orderMapper.updateByPrimaryKeySelective(record);
//		OmsOrderOperateHistory history = new OmsOrderOperateHistory();
		return 0;
	}

}
