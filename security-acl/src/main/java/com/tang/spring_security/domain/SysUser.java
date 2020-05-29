package com.tang.spring_security.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Data
public class SysUser implements UserDetails {

    private Integer id;

    private String username;

    private String password;

    private Date createTime;

    private Date lastLoginDate;

    private boolean isAccountNonExpired;

    private boolean isAccountNonLocked;

    private Collection<? extends GrantedAuthority> authorities;

    private boolean isCredentialsNonExpired;

    private boolean enabled;

}
