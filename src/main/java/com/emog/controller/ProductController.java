package com.emog.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emog.mapper.ProductMapper;
import com.emog.model.Product;
import com.emog.service.ProductService;


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
	
}
