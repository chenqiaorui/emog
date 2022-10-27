package com.emog.service.impl;

import com.emog.dto.PmsProductParam;
import com.emog.mapper.PmsProductMapper;
import com.emog.model.PmsProduct;
import com.emog.model.PmsProductExample;
import com.emog.service.PmsProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<PmsProduct> list(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        PmsProductExample productExample = new PmsProductExample();
        PmsProductExample.Criteria criteria = productExample.createCriteria();
        criteria.andDeleteStatusEqualTo(0);
        return productMapper.selectByExample(productExample);
    }

}
