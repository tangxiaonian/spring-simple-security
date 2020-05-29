package com.tang.userservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(value = "管理员接口")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @ApiOperation(("管理员信息"))
    @GetMapping("/info")
    public Map<String, String> getUserInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("username", "admin");
        map.put("password", "123");
        return map;
    }
}
