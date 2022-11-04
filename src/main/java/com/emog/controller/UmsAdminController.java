package com.emog.controller;

import com.emog.common.CommonResult;
import com.emog.dto.UmsAdminLoginParam;
import com.emog.dto.UmsAdminParam;
import com.emog.model.UmsAdmin;
import com.emog.service.UmsAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class UmsAdminController {

    @Autowired
    UmsAdminService adminService;

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UmsAdmin> register(@Validated @RequestBody UmsAdminParam adminParam) {
        System.out.println("UmsAdmin call...");
        UmsAdmin admin = adminService.register(adminParam);
        if(admin != null) {
            return CommonResult.success(admin);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestBody UmsAdminLoginParam umsAdminLoginParam) {
        String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        return CommonResult.success(token);
    }
}
