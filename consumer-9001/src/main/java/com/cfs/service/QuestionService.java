package com.cfs.service;

import com.cfs.entities.CommonResult;
import com.cfs.entities.QuestionPublicSc;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;

/**
 * @Author Ke
 * @Date 2022/3/12 21:18
 * @Description
 * @Version 1.0
 */
@Service
@FeignClient(name = "nacos-provider")
public interface QuestionService {

    @PostMapping(value = "/question/choice/addQuestion")
    public CommonResult<String> addQuestion(@RequestBody HashMap<String,String> map);

    @PostMapping(value = "/question/choice/getQuestion")
    public CommonResult<QuestionPublicSc> getQuestionById(@RequestBody HashMap<String,String> map);

    @PostMapping(value = "/question/choice/getAllQuestion")
    public CommonResult<List<QuestionPublicSc>> getAllQuestion(@RequestBody HashMap<String,String> map);

    @PostMapping(value = "/question/choice/checkUser")
    public CommonResult<String> checkUser(@RequestBody HashMap<String,String> map);

    @PostMapping(value = "/question/choice/deleteQuestion")
    public CommonResult deleteQuestion(@RequestBody HashMap<String,String> map);

    @PostMapping(value = "/question/choice/updateQuestion")
    public CommonResult<QuestionPublicSc> updateQuestion(@RequestBody HashMap<String,String> map);

    @PostMapping(value = "/question/checkUser2")
    public CommonResult<String> checkUser2(@RequestBody HashMap<String, String> map);

    @PostMapping(value = "/question/getNoExamineQuestion")
    public CommonResult<List<QuestionPublicSc>> getNoExamineQuestion(@RequestBody HashMap<String, String> map);

    @PostMapping(value = "/question/examineQuestion")
    public CommonResult<String> examine(@RequestBody HashMap<String, String> map);

}
