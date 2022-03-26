package com.cfs.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Ke
 * @Date 2022/2/9 15:26
 * @Description
 * @Version 1.0
 */
@RestController
@Slf4j
public class TestController {

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Long id){
        return "nacos registry,serverPort:" + port + "id:" + id;
    }
}