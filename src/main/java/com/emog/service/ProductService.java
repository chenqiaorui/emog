package com.emog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emog.dto.ProductParam;
import com.emog.mapper.ProductMapper;
import com.emog.model.Product;

//@Service相当注入productService对象实例到容器中
@Service
public class ProductService {
	
	@Autowired
	private ProductMapper productMapper;

	public Product findByName(String name) {
		return this.productMapper.findByName(name);
	}
	
	public int delByName(String name) {
		return this.productMapper.delByName(name);
	}
	
	public boolean UpdateById(ProductParam productParam) {
		return this.productMapper.UpdateById(productParam);
	}
	

}
