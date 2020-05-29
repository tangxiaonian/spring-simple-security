package com.tang.userservice.service.impl;

import com.tang.userservice.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserServiceImpl implements UserService {

    @Resource
    public UserDetailsService userDetailsServiceImpl;

    @Resource
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails login(String username, String password) {
        // 加载用户信息
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);

        // 验证密码是否正确
        if (password.equals(userDetails.getPassword())) {
            return userDetails;
        }
        return null;
    }
}
