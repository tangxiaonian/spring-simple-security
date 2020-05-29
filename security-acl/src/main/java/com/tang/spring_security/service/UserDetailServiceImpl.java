package com.tang.spring_security.service;

import com.tang.spring_security.domain.SysUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    public BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 查询数据库
        SysUser sysUser = new SysUser();

        sysUser.setUsername(username);

        sysUser.setPassword("123");

        if (username.equals("tang")) {
            sysUser.setAuthorities(Collections.singletonList(
                    new SimpleGrantedAuthority("ROLE_USER")));
        }else {
            sysUser.setAuthorities(Collections.singletonList(
                    new SimpleGrantedAuthority("ROLE_ADMIN")));
        }

        return sysUser;
    }
}
