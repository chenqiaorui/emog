package com.emog.controller;



import java.util.List;

import com.emog.common.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emog.dto.OmsOrderQueryParam;
import com.emog.dto.ProductParam;
import com.emog.model.OmsOrder;
import com.emog.model.Product;
import com.emog.service.OmsOrderService;
import com.emog.service.ProductService;
import com.emog.service.SmsFlashPromotionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Controller
@RequestMapping("/flash")
@Api(tags = "SmsFlashPromotionController", description = "限时购管理")
//@Api(tags = "订单列表")
public class SmsFlashPromotionController {
	
	@Autowired
    private SmsFlashPromotionService flashPromotionService;

    @ApiOperation("更新上下线状态")
    @RequestMapping(value = "/update/status/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id, Integer status) {
    	int count = flashPromotionService.updateStatus(id, status);
    	if(count > 0) {
    		return CommonResult.success(count);
    	}
    	return CommonResult.failed();
        
    }

}
