package com.tang.spring_security.handler;

import com.tang.spring_security.utils.JwtTokenUtils;
import com.tang.spring_security.utils.R;
import com.tang.spring_security.utils.ResponseUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {

        System.out.println("===============登录成功============");

        String username = ((UserDetails)authentication.getPrincipal()).getUsername();

        // 创建token返回
        String token = JwtTokenUtils.generateToken(username);

        ResponseUtil.out(httpServletResponse, R.ok().message("登录成功!").data(token));
    }
}
