package com.cfs.controller;

import com.cfs.entities.CommonResult;
import com.cfs.entities.QuestionEntity;
import com.cfs.entities.QuestionPublicComp;
import com.cfs.entities.QuestionPublicSc;
import com.cfs.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    QuestionService questionService;

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

    @PostMapping(value = "/completion/addQuestion")
    public CommonResult<String> addQuestionCp(@RequestBody HashMap<String, String> map){

        return questionService.addQuestionCp(map);

    }

    @PostMapping(value = "/completion/deleteQuestion")
    public CommonResult<String> deleteQuestionCp(@RequestBody HashMap<String, String> map){

        return questionService.deleteQuestionCp(map);

    }

    @PostMapping(value = "/completion/updateQuestion")
    public CommonResult<String> updateQuestionCp(@RequestBody HashMap<String, String> map){

        return questionService.updateQuestionCp(map);

    }

    @PostMapping(value = "/completion/getQuestion")
    public CommonResult<QuestionPublicComp> getQuestionCp(@RequestBody HashMap<String, String> map){

        return questionService.getQuestionCp(map);

    }

    @PostMapping(value = "/completion/getAllQuestion")
    public CommonResult<List<QuestionPublicComp>> getAllQuestionCp(@RequestBody HashMap<String, String> map){

        return questionService.getAllQuestionCp(map);

    }

    @PostMapping(value = "/getQuestion")
    public CommonResult<Object> getQuestion(@RequestBody HashMap<String, Object> map){

        return questionService.getQuestion(map);

    }

    @PostMapping(value = "/getQuestionEntity")
    public CommonResult<List<QuestionEntity>> getQuestionEntity(@RequestBody HashMap<String, Object> map){

        return questionService.getQuestionEntity(map);

    }


}