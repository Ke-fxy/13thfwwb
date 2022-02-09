package com.cfs.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author Ke
 * @Date 2022/2/9 16:30
 * @Description
 * @Version 1.0
 */
@Service
@FeignClient(value = "nacos-provider")
public interface TestService {

    @GetMapping(value = "/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Long id);

}
