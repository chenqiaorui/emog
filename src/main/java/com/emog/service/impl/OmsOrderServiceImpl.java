package com.emog.service.impl;

import java.util.List;

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

	@Override
	public List<OmsOrder> list(OmsOrderQueryParam queryParam, Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		return orderDao.getList(queryParam);
	}

}
