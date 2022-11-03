package com.emog.service.impl;

import com.emog.dto.UmsAdminParam;
import com.emog.mapper.UmsAdminMapper;
import com.emog.model.UmsAdmin;
import com.emog.model.UmsAdminExample;
import com.emog.service.UmsAdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UmsAdminServiceImpl implements UmsAdminService {
    @Autowired
    private UmsAdminMapper adminMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UmsAdmin register(UmsAdminParam adminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(adminParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        //1.查询是否有相同用户名的用户
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        List<UmsAdmin> umsAdminList = adminMapper.selectByExample(example);
        if (umsAdminList.size() > 0) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        adminMapper.insert(umsAdmin);
        return umsAdmin;
    }
}
