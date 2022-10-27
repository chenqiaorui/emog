package com.emog.service.impl;

import com.emog.dto.PmsProductParam;
import com.emog.mapper.PmsProductMapper;
import com.emog.model.PmsProduct;
import com.emog.service.PmsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
// 标注PmsProductService作为一个service能被@Autowired
public class PmsProductServiceImpl implements PmsProductService {
    @Autowired
    private PmsProductMapper productMapper;

    @Override
    public int create(PmsProductParam productParam) {
        PmsProduct product = productParam;
//        product.setId(null);
        System.out.println(product);
        int count = productMapper.insertSelective(product);
        return count;
    }

    @Override
    public int update(Long id, PmsProductParam productParam) {
        PmsProduct product = productParam;
        product.setId(id);
//        System.out.println(productParam);
        productMapper.updateByPrimaryKeySelective(product);
        return 1;
    }
}
