package com.tang.userservice.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    public UserDetails login(String username, String password);

}
