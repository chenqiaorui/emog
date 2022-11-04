package com.emog.service.impl;

import com.emog.bo.AdminUserDetails;
import com.emog.dto.UmsAdminParam;
import com.emog.mapper.UmsAdminMapper;
import com.emog.model.UmsAdmin;
import com.emog.model.UmsAdminExample;
import com.emog.service.UmsAdminService;
import com.emog.util.JwtTokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    public UmsAdmin register(UmsAdminParam adminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(adminParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
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

    @Override
    public String login(String username, String password) {
        String token = null;
        UserDetails userDetails = loadUserByUsername(username);
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            System.out.println("密码不正确");
        }
        if(!userDetails.isEnabled()){
            System.out.println("帐号已被禁用");
        }
        token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UmsAdmin admin = null;
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            admin = adminList.get(0);
            return new AdminUserDetails(admin);
        }
        return null;
    }

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdmin admin = null;

        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            admin = adminList.get(0);
            return admin;
        }
        return null;
    }



}
