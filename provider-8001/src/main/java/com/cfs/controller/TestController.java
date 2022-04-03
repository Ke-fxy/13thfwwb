package com.cfs.controller;

import com.cfs.entities.CommonResult;
import com.cfs.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @Author Ke
 * @Date 2022/3/24 20:13
 * @Description
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping(value = "/test")
public class TestController {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    TestService testService;

    private String checkup(String token) {

        ValueOperations<String, String> forValue = stringRedisTemplate.opsForValue();
        String s = forValue.get("userToken:" + token);
        if (s == null || s.length() == 0) {
            return null;
        }
        return s;

    }

    @PostMapping(value = "/createTest")
    public CommonResult<String> createTest(@RequestBody HashMap<String,Object> map){

        String token = String.valueOf(map.get("token"));
        String checkup = checkup(token);

        if (checkup == null) {
            return new CommonResult<>(200, "用户未登录或登录状态失效", null);
        }

        String testName = String.valueOf(map.get("testName"));
        Integer paperId = (Integer) map.get("paperId");


        return null;
    }

}