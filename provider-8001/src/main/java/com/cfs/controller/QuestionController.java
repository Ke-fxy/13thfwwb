package com.cfs.controller;

import com.cfs.entities.CommonResult;
import com.cfs.entities.QuestionPublicSc;
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
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    public CommonResult<String> addQuestion(@RequestBody HashMap<String,String> map){

        String token = checkup(map.get("token"));
        String text = map.get("text");
        String option1 = map.get("option1");
        String option2 = map.get("option2");
        String option3 = map.get("option3");
        String option4 = map.get("option4");
        String answer = map.get("answer");
        Integer createrId = Integer.valueOf(map.get("createrId"));
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        System.out.println(createTime);
        Integer chapterId = Integer.valueOf(map.get("chapterId"));
        Integer modularId = Integer.valueOf(map.get("modularId"));
        Integer diffculyt = Integer.valueOf(map.get("diffculyt"));

        if (token==null){
            return new CommonResult(200,"用户未登录或登录状态失效");
        }

        Integer integer = questionService.addQuestion(null,text, option1, option2, option3, option4, answer, createrId, createTime, chapterId, modularId, diffculyt);

        if (integer==0){
            return new CommonResult(200,"插入失败");
        }else {
            return new CommonResult(100,"插入成功");
        }


    }

    @PostMapping(value = "/getQuestion")
    public CommonResult<QuestionPublicSc> getQuestionById(@RequestBody HashMap<String,String> map){

        String token = map.get("token");
        String checkup = checkup(token);

        if (checkup==null){
            return new CommonResult(200,"用户未登录或登录状态失效",null);
        }

        Integer id = Integer.parseInt(map.get("id"));
        QuestionPublicSc questionById = questionService.getQuestionById(id);

        if (questionById != null){
            return new CommonResult(100,"查询成功",questionById);
        }else {
            return new CommonResult(200,"未查询到此题",null);
        }

    }

    @PostMapping(value = "/getAllQuestion")
    public CommonResult<List<QuestionPublicSc>> getAllQuestion(@RequestBody HashMap<String,String> map){

        String token = map.get("token");
        String checkup = checkup(token);

        if (checkup==null){
            return new CommonResult(200,"用户未登录或登录状态失效",null);
        }

        List<QuestionPublicSc> allQuestion = questionService.getAllQuestion();

        if (allQuestion!=null&&allQuestion.size()!=0){
            return new CommonResult<>(100,"查询成功",allQuestion);
        }else {
            return new CommonResult<>(200,"查询失败");
        }
    }

    @PostMapping(value = "/checkUser")
    public CommonResult<String> checkUser(@RequestBody HashMap<String,String> map){

        String token = map.get("token");
        String checkup = checkup(token);

        if (checkup==null){
            return new CommonResult(200,"用户未登录或登录状态失效",null);
        }

        Integer userId = Integer.parseInt(map.get("userId"));
        boolean b = questionService.checkUser(userId);

        if (b){
            return new CommonResult<>(100,"当前用户可上传题目");
        }else {
            return new CommonResult<>(200,"当前用户无法上传题目");
        }

    }
}