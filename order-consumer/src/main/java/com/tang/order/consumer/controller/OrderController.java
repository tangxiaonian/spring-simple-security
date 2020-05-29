package com.tang.order.consumer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(value = "Order接口")
@RestController
@RequestMapping("/order")
public class OrderController {

    @ApiOperation("Order信息")
    @GetMapping("/info")
    public Map<String, String> getOrderInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("username", "order");
        map.put("password", "123");
        return map;
    }
}
