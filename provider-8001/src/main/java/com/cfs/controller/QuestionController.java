package com.cfs.controller;

import com.cfs.entities.CommonResult;
import com.cfs.service.QuestionService;
import com.cfs.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.HashMap;

/**
 * @Author Ke
 * @Date 2022/3/6 21:21
 * @Description
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping(value = "/question")
public class QuestionController {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    QuestionService questionService;

    private String checkup(String token) {

        ValueOperations<String, String> forValue = stringRedisTemplate.opsForValue();
        String s = forValue.get("userToken:" + token);
        if (s==null||s.length()==0){
            return null;
        }
        return s;

    }


    @PostMapping(value = "/addQuestion")
    public CommonResult addQuestion(@RequestBody HashMap<String,String> map){

        String token = checkup(map.get("token"));
        String text = map.get("text");
        String option1 = map.get("option1");
        String option2 = map.get("option2");
        String option3 = map.get("option3");
        String option4 = map.get("option4");
        String answer = map.get("answer");
        Integer createrId = Integer.valueOf(map.get("createrId"));
        Time createTime = Time.valueOf(map.get("createTime"));
        Integer chapterId = Integer.valueOf(map.get("chapterId"));
        Integer modularId = Integer.valueOf(map.get("modularId"));
        Integer diffculyt = Integer.valueOf(map.get("diffculyt"));

        if (token==null){
            return new CommonResult(200,"用户未登录或登录状态失效");
        }

        Integer integer = questionService.addQuestion(text, option1, option2, option3, option4, answer, createrId, createTime, chapterId, modularId, diffculyt);

        if (integer==0){
            return new CommonResult<String>(200,"插入失败");
        }else {
            return new CommonResult<String>(100,"插入成功");
        }


    }

}