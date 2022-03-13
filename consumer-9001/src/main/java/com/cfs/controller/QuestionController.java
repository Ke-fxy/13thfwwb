package com.cfs.controller;

import com.cfs.entities.CommonResult;
import com.cfs.entities.QuestionPublicSc;
import com.cfs.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Ke
 * @Date 2022/3/6 21:21
 * @Description
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping(value = "/question")
@CrossOrigin(value = "*",maxAge = 3600)
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


    @PostMapping(value = "/choice/addQuestion")
    public CommonResult<String> addQuestion(@RequestBody HashMap<String,String> map){

        return questionService.addQuestion(map);

    }

    @PostMapping(value = "/choice/getQuestion")
    public CommonResult<QuestionPublicSc> getQuestionById(@RequestBody HashMap<String,String> map){

        return questionService.getQuestionById(map);

    }

    @PostMapping(value = "/choice/getAllQuestion")
    public CommonResult<List<QuestionPublicSc>> getAllQuestion(@RequestBody HashMap<String,String> map){

        return questionService.getAllQuestion(map);

    }

    @PostMapping(value = "/choice/checkUser")
    public CommonResult<String> checkUser(@RequestBody HashMap<String,String> map){

        return questionService.checkUser(map);

    }

    @PostMapping(value = "/choice/deleteQuestion")
    public CommonResult<String> deleteQuestion(@RequestBody HashMap<String,String> map){

        return questionService.deleteQuestion(map);

    }

    @PostMapping(value = "/choice/updateQuestion")
    public CommonResult<QuestionPublicSc> updateQuestion(@RequestBody HashMap<String,String> map){

        return questionService.updateQuestion(map);

    }

    @PostMapping(value = "/checkUser2")
    public CommonResult<String> checkUser2(@RequestBody HashMap<String, String> map) {

        return questionService.checkUser2(map);

    }

    @PostMapping(value = "/getNoExamineQuestion")
    public CommonResult<List<QuestionPublicSc>> getNoExamineQuestion(@RequestBody HashMap<String, String> map) {

        return questionService.getNoExamineQuestion(map);

    }

    @PostMapping(value = "/examineQuestion")
    public CommonResult<String> examine(@RequestBody HashMap<String, String> map) {

        return questionService.examine(map);

    }


}