package com.tang.userservice.controller;

import com.tang.spring_security.utils.JwtTokenUtils;
import com.tang.spring_security.utils.R;
import com.tang.userservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(value = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    public UserService userServiceImpl;

    @ApiOperation("用户信息")
    @GetMapping("/info")
    public Map<String, String> getUserInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("username", "tang");
        map.put("password", "721000");
        return map;
    }

    @PostMapping("/login")
    public R login(String username,String password) {

        System.out.println( "username=====>" + username + "    password====>" + password );

        UserDetails userDetails = userServiceImpl.login(username, password);

        System.out.println("userDetails---->" + userDetails);

        // 创建token返回
        String token = JwtTokenUtils.generateToken(username);

        return R.ok().message("登录成功!").data(token);
    }

}
