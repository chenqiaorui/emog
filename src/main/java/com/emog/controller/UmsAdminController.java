package com.emog.controller;

import com.emog.common.CommonResult;
import com.emog.dto.UmsAdminParam;
import com.emog.model.UmsAdmin;
import com.emog.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class UmsAdminController {

    @Autowired
    UmsAdminService adminService;

    public CommonResult<UmsAdmin> register(@Validated @RequestParam UmsAdminParam adminParam) {
        UmsAdmin admin = adminService.register(adminParam);
        if(admin != null) {
            return CommonResult.success(admin);
        } else {
            return CommonResult.failed();
        }

    }
}
