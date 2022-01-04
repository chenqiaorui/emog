package com.emog.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emog.common.CommonPage;
import com.emog.common.CommonResult;
import com.emog.model.SmsHomeBrand;
import com.emog.service.SmsHomeBrandService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Controller
@RequestMapping("/home/brand")
@Api(tags = "SmsHomeBrandController", description = "首页品牌管理")
public class SmsHomeBrandController {
	
	@Autowired
    private SmsHomeBrandService homeBrandService;

    @ApiOperation("分页查询推荐品牌")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<SmsHomeBrand>> list(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize, 
    		                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
    	List<SmsHomeBrand> list = homeBrandService.list(pageSize, pageNum);
    	return CommonResult.success(CommonPage.restPage(list));        
    }
    
    @ApiOperation("批量删除推荐品牌")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody  
    public CommonResult<Integer> delete(@RequestParam("ids")List<Long> ids) {
    	int count = homeBrandService.delete(ids);
    	if (count > 0) {
    		return CommonResult.success(count);
    	}
    	return CommonResult.failed();
	}
    
    @ApiOperation("添加首页推荐品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody List<SmsHomeBrand> homeBrandList) {
        int count = homeBrandService.create(homeBrandList);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
    

}
