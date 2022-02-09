package com.cfs.controller;

import com.cfs.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author Ke
 * @Date 2022/2/9 16:24
 * @Description
 * @Version 1.0
 */
@RestController
@Slf4j
public class TestController {

    @Resource
    TestService testService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public String getPaymentById(@PathVariable("id") Long id){
        return testService.getPayment(id);
    }


}