package com.emog.service.impl;

import com.emog.dto.UmsAdminParam;
import com.emog.mapper.UmsAdminMapper;
import com.emog.model.UmsAdmin;
import com.emog.model.UmsAdminExample;
import com.emog.service.UmsAdminService;
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

    @Override
    public String login(String username, String password) {
//        UserDetails userDetails = loadUserByUsername(username);
        return null;
    }

//    public UserDetails loadUserByUsername(String username){
//        //获取用户信息
//        UmsAdmin admin = getAdminByUsername(username);
//        if (admin != null) {
//            List<UmsResource> resourceList = getResourceList(admin.getId());
//            return new AdminUserDetails(admin,resourceList);
//        }
//        throw new UsernameNotFoundException("用户名或密码错误");
//    }
//
//    public UmsAdmin getAdminByUsername(String username) {
////        UmsAdmin admin = getCacheService().getAdmin(username);
////        if(admin!=null) return  admin;
//        UmsAdminExample example = new UmsAdminExample();
//        example.createCriteria().andUsernameEqualTo(username);
//        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
//        if (adminList != null && adminList.size() > 0) {
//            admin = adminList.get(0);
//            getCacheService().setAdmin(admin);
//            return admin;
//        }
//        return null;
//    }

    @Override
    public UmsAdminCacheService getCacheService() {
        return SpringUtil.getBean(UmsAdminCacheService.class);
    }
}
