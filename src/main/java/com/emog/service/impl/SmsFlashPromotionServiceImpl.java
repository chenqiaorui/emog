package com.emog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emog.mapper.SmsFlashPromotionMapper;
import com.emog.model.SmsFlashPromotion;
import com.emog.service.SmsFlashPromotionService;

@Service
public class SmsFlashPromotionServiceImpl implements SmsFlashPromotionService {
	
	@Autowired
    private SmsFlashPromotionMapper flashPromotionMapper;
	
	@Override
	public int updateStatus(Long id, Integer status) {
		SmsFlashPromotion smsFlashPromotion = new SmsFlashPromotion();
		smsFlashPromotion.setId(id);
		smsFlashPromotion.setStatus(status);
		int count = flashPromotionMapper.updateByPrimaryKeySelective(smsFlashPromotion);
		return count;
	}

	

}
