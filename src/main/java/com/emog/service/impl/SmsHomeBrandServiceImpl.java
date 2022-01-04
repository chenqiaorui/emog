package com.emog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emog.mapper.SmsHomeBrandMapper;
import com.emog.model.SmsHomeBrand;
import com.emog.model.SmsHomeBrandExample;
import com.emog.service.SmsHomeBrandService;
import com.github.pagehelper.PageHelper;

@Service
public class SmsHomeBrandServiceImpl implements SmsHomeBrandService {
	
	@Autowired
    private SmsHomeBrandMapper homeBrandMapper;

	@Override
	public List<SmsHomeBrand> list(Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum,pageSize);
		SmsHomeBrandExample example = new SmsHomeBrandExample();
		SmsHomeBrandExample.Criteria criteria = example.createCriteria();
		return homeBrandMapper.selectByExample(example);
	}

	

}
