package com.cfs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author Ke
 * @Date 2022/2/9 16:18
 * @Description
 * @Version 1.0
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.cfs.service")
public class ConsumerMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerMain9001.class,args);
    }
}