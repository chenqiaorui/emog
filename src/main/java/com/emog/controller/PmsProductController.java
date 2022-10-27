package com.emog.controller;



import com.emog.common.CommonResult;
import com.emog.dto.PmsProductParam;
import com.emog.model.PmsProduct;
import com.emog.service.PmsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.emog.dto.ProductParam;
import com.emog.model.Product;
import com.emog.service.ProductService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;


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

	@RequestMapping(value = "update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult update(@PathVariable Long id, @RequestBody PmsProductParam productParam) {
		int count = productService.update(id, productParam);
		if (count > 0) {
			return CommonResult.success(count);
		} else {
			return CommonResult.failed();
		}
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<PmsProduct>> getList(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
								@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		List<PmsProduct> productList = productService.list(pageSize, pageNum);
		return CommonResult.success(productList);
	}
	

	
}
