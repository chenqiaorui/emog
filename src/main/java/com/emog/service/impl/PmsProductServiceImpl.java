package com.emog.service.impl;

import cn.hutool.core.util.StrUtil;
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

    @Override
    public int updateDeleteStatus(List<Long> ids, Integer deleteStatus) {
        PmsProduct record = new PmsProduct();
        record.setDeleteStatus(deleteStatus);
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andIdIn(ids);
        return productMapper.updateByExampleSelective(record, example);
    }

    @Override
    public List<PmsProduct> list(String keyword) {
        PmsProductExample productExample = new PmsProductExample();
        PmsProductExample.Criteria criteria = productExample.createCriteria();
        criteria.andDeleteStatusEqualTo(0);
        if(!StrUtil.isEmpty(keyword)){
            criteria.andNameLike("%" + keyword + "%");
            productExample.or().andDeleteStatusEqualTo(0);
        }
        return productMapper.selectByExample(productExample);
    }

}
