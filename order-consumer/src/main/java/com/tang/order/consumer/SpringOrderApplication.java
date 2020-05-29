package com.tang.order.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication(scanBasePackages = {"com.tang"})
public class SpringOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringOrderApplication.class, args);
    }
}
