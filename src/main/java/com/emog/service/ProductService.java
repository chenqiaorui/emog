package com.emog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emog.dto.ProductParam;
import com.emog.mapper.ProductMapper;
import com.emog.model.Product;
import com.github.pagehelper.Page;

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
	
	public Page<Product> listAll(Integer pageNum, Integer pageSize) {
		return this.productMapper.listAll(pageNum, pageSize);
	}
	
	public boolean insert(Product product) {
		return this.productMapper.insert(product);
	}
	

}
