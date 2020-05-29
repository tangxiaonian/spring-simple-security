package com.tang.userservice.domain;

import lombok.Data;

@Data
public class WebLog {
    /**
     * 操作描述
     */
    private String description;

    /**
     * 操作用户
     */
    private String username;

    /**
     * 操作时间
     */
    private Long startTime;

    /**
     * 消耗时间
     */
    private Integer spendTime;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * URI
     */
    private String uri;

    /**
     * URL
     */
    private String url;

    /**
     * 请求类型
     */
    private String type;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 请求参数
     */
    private Object parameter;

    /**
     * 请求返回的结果
     */
    private Object result;

    /**
     * 方法执行时是否产生了异常
     */
    private boolean isError;

    /**
     * 异常信息
     */
    private String errorMsg;

    //省略了getter,setter方法
}