package com.emog.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emog.dto.ProductParam;
import com.emog.mapper.ProductMapper;
import com.emog.model.Product;
import com.emog.service.ProductService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


@Controller
@RequestMapping("/product")
public class ProductController {
  
	// @Autowired相当初始化了productService对象
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductMapper productMapper;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	// @ResponseBody定义了返回字符格式，不添加此注解默认视为view视图
	@ResponseBody
	public Product findByName(String name) {
		return this.productService.findByName(name);
	}
	
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	@ResponseBody
	public int delByName(@RequestParam("name") String name) {
		return this.productService.delByName(name);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public boolean UpdateById(@RequestBody ProductParam productParam) {
		return this.productService.UpdateById(productParam);
	}
	
	/**
	 * @param pageNum 当前处于第几页
	 * @param pageSize 每页有几条数据
	 * 
	 * 
	 * */
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public Page<Product> listAll(@RequestParam(defaultValue="1") Integer pageNum, @RequestParam(defaultValue="10") Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return this.productService.listAll(pageNum, pageSize);
	} 
	
}
