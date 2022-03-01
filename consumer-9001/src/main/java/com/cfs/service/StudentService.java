package com.cfs.service;

import com.cfs.entities.CommonResult;
import com.cfs.entities.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author Ke
 * @Date 2022/2/10 14:19
 * @Description
 * @Version 1.0
 */
@Service
@FeignClient(name = "nacos-provider")
public interface StudentService {

    @GetMapping(value = "/student/login")
    public CommonResult login(@RequestParam("sNo") Integer sNo,
                                       @RequestParam("password") String password);

    @PostMapping(value = "/student/getCode")
    public CommonResult getCode(@RequestBody Map map);

    @PostMapping(value = "/student/changePwd")
    public CommonResult changePwd(@RequestBody Map map);
}
