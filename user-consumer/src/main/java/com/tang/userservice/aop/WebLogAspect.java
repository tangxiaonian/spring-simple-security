package com.tang.userservice.aop;

import cn.hutool.json.JSONUtil;
import com.tang.userservice.domain.WebLog;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class WebLogAspect {

    @Pointcut(value = "execution(public * com.tang.userservice.controller..*.*(..))")
    public void pointCut() { }

    @Around(value = "pointCut()")
    public Object doAround(JoinPoint joinPoint) {

        long startTime = System.currentTimeMillis();
        // 获取方法签名
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();

        ProceedingJoinPoint proceedingJoinPoint = (ProceedingJoinPoint) joinPoint;

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        // 获取request请求
        HttpServletRequest request = servletRequestAttributes.getRequest();

        WebLog webLog = new WebLog();
        // 设置日志参数
        webLog.setStartTime(startTime);
        webLog.setIp(request.getRemoteAddr());
        webLog.setType(request.getMethod());
        webLog.setUri(request.getRequestURI());
        webLog.setUrl(request.getRequestURL().toString());
        webLog.setError(false);

        Method method = methodSignature.getMethod();
        // 设置请求参数
        webLog.setParameter(getParameter(method,joinPoint.getArgs()));

        webLog.setMethodName(method.getDeclaringClass().getName()+"."+method.getName());
        // 注解存在
        if (method.isAnnotationPresent(ApiOperation.class)) {
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            webLog.setDescription(apiOperation.value());
        }else {
            webLog.setDescription("暂无介绍....");
        }

        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
            // 设置结果
            webLog.setResult(result);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            webLog.setError(true);
            webLog.setErrorMsg(throwable.toString());
        }finally {
            // 消耗时间
            webLog.setSpendTime(Long.valueOf(System.currentTimeMillis() - startTime).intValue());
            log.info("{}", JSONUtil.parse(webLog).toJSONString(2));
        }

        return result;
    }

    /**
     * 参数值 k:V 形式
     * @param method
     * @param args
     * @return
     */
    private Object getParameter(Method method, Object[] args) {

        Parameter[] parameters = method.getParameters();

        List<Map<String,Object>> argList = new ArrayList<>();

        for (int i = 0; i < parameters.length; i++) {

            Parameter parameter = parameters[i];

            Map<String, Object> map = new HashMap<>();

            map.put(parameter.getName(), args[i]);

            argList.add(map);
        }

        return argList;
    }

}