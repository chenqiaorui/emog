package com.emog.controller;



import com.emog.common.CommonResult;
import com.emog.dto.PmsProductParam;
import com.emog.model.PmsProduct;
import com.emog.service.PmsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;


@Api(tags = "PmsProductController")
@Controller
@RequestMapping("/product")
public class PmsProductController {
  
	// @Autowired相当初始化了productService对象
	@Autowired
	private PmsProductService productService;

	@ApiOperation("创建商品")
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

	@ApiOperation("更新商品")
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

	@ApiOperation("批量修改删除状态")
	@RequestMapping(value = "/update/deleteStatus", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Integer> updateDeleteStatus(@RequestParam("ids") List<Long> ids,
								@RequestParam("deleteStatus") Integer deleteStatus) {
		int count = productService.updateDeleteStatus(ids, deleteStatus);
		return CommonResult.success(count);
	}

	@ApiOperation("根据商品名称模糊查询")
	@RequestMapping(value = "simpleList", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<PmsProduct>> getList(String keyword) {
		List<PmsProduct> productList = productService.list(keyword);
		return CommonResult.success(productList);
	}

	@ApiOperation("查询商品")
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<PmsProduct>> getList(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
												  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		List<PmsProduct> productList = productService.list(pageSize, pageNum);
		return CommonResult.success(productList);
	}
	

	
}
