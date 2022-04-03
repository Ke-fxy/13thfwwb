package com.cfs.service;

import com.cfs.entities.CommonResult;
import com.cfs.entities.QuestionEntity;
import com.cfs.entities.QuestionPublicComp;
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

    @PostMapping(value = "/question/completion/addQuestion")
    public CommonResult<String> addQuestionCp(@RequestBody HashMap<String, String> map);

    @PostMapping(value = "/question/completion/deleteQuestion")
    public CommonResult<String> deleteQuestionCp(@RequestBody HashMap<String, String> map);

    @PostMapping(value = "/question/completion/updateQuestion")
    public CommonResult<String> updateQuestionCp(@RequestBody HashMap<String, String> map);

    @PostMapping(value = "/question/completion/getQuestion")
    public CommonResult<QuestionPublicComp> getQuestionCp(@RequestBody HashMap<String, String> map);

    @PostMapping(value = "/question/completion/getAllQuestion")
    public CommonResult<List<QuestionPublicComp>> getAllQuestionCp(@RequestBody HashMap<String, String> map);

    @PostMapping(value = "/question/getQuestion")
    public CommonResult<Object> getQuestion(@RequestBody HashMap<String, Object> map);

    @PostMapping(value = "/question/getQuestionEntity")
    public CommonResult<List<QuestionEntity>> getQuestionEntity(@RequestBody HashMap<String, Object> map);

}
