package com.emog.controller;



import com.emog.common.CommonResult;
import com.emog.dto.PmsProductParam;
import com.emog.service.PmsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emog.dto.ProductParam;
import com.emog.model.Product;
import com.emog.service.ProductService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Controller
@RequestMapping("/product")
@Api(tags = "商品列表")
public class PmsProductController {
  
	// @Autowired相当初始化了productService对象
	@Autowired
	private PmsProductService productService;

	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult create(@RequestBody PmsProductParam productParam) {
		System.out.println(productParam.toString());
		int count = productService.create(productParam);
		if (count > 0) {
			return CommonResult.success(count);
		} else {
			return CommonResult.failed();
		}
	}
	

	
}
