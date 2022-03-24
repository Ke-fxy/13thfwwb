package com.cfs.controller;

import com.cfs.entities.CommonResult;
import com.cfs.entities.Paper;
import com.cfs.entities.PaperQuestion;
import com.cfs.service.PaperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;

@RestController
@Slf4j
@RequestMapping(value = "/paper")
public class PaperController {

    @Resource
    PaperService paperService;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    private String checkup(String token) {

        ValueOperations<String, String> forValue = stringRedisTemplate.opsForValue();
        String s = forValue.get("userToken:" + token);
        if (s == null || s.length() == 0) {
            return null;
        }
        return s;
    }

    @RequestMapping("/addPaper")
    public CommonResult<String> addPaper(@RequestBody HashMap<String, Object> map){
        String token = (String)map.get("token");
        String checkup = checkup(token);

        if (checkup == null) {
            return new CommonResult<>(200, "用户未登录或登录状态失效", null);
        }

        String paperName = (String)map.get("paperName");
        Integer maxMark = Integer.parseInt(map.get("maxMark").toString());
        Integer creatorId = Integer.parseInt(checkup);
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        Integer courseId = Integer.parseInt(map.get("courseId").toString());
        Integer change = Integer.parseInt(map.get("change").toString());

        Paper paper = new Paper(null,paperName,maxMark,creatorId,courseId,createTime,change);
        Integer result = paperService.addPaper(paper);

        return result > 0 ? new CommonResult<>(100, "添加成功") : new CommonResult<>(200, "添加失败");
    }

    @RequestMapping("/addPaperQuestion")
    public CommonResult<String> addPaperQuestion(@RequestBody HashMap<String, Object> map){
        String token = (String)map.get("token");
        String checkup = checkup(token);

        if (checkup == null) {
            return new CommonResult<>(200, "用户未登录或登录状态失效", null);
        }
        Integer paperId = Integer.parseInt(map.get("paperId").toString());

        List<Map> questionList = (List<Map>) map.get("questionList");

        Integer result = paperService.addPaperQuestion(paperId,questionList);

        return result > 0 ? new CommonResult<>(100, "添加成功") : new CommonResult<>(200, "添加失败");
    }

    @RequestMapping("/deletePaper")
    public CommonResult<String> deletePaper(@RequestBody HashMap<String, Object> map){
        String token = (String)map.get("token");
        String checkup = checkup(token);

        if (checkup == null) {
            return new CommonResult<>(200, "用户未登录或登录状态失效", null);
        }
        Integer paperId = Integer.parseInt(map.get("paperId").toString());


        Integer result = paperService.deletePaper(paperId);

        return result > 0 ? new CommonResult<>(100, "删除成功") : new CommonResult<>(200, "删除失败");
    }

    @RequestMapping("/updatePaper")
    public CommonResult<String> updatePaper(@RequestBody HashMap<String, Object> map){
        String token = (String)map.get("token");
        String checkup = checkup(token);

        if (checkup == null) {
            return new CommonResult<>(200, "用户未登录或登录状态失效", null);
        }

        Integer paperId = Integer.parseInt(map.get("paperId").toString());
        String paperName = (String)map.get("paperName");
        Integer maxMark = Integer.parseInt(map.get("maxMark").toString());
        Integer creatorId = Integer.parseInt(checkup);
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        Integer courseId = Integer.parseInt(map.get("courseId").toString());
        Integer change = Integer.parseInt(map.get("change").toString());

        Paper paper = new Paper(paperId,paperName,maxMark,creatorId,courseId,createTime,change);
        Integer result = paperService.updatePaper(paper);

        return result > 0 ? new CommonResult<>(100, "修改成功") : new CommonResult<>(200, "修改失败");
    }

    @RequestMapping("/getPaper")
    public CommonResult<Paper> getPaper(@RequestBody HashMap<String, Object> map){
        String token = (String)map.get("token");
        String checkup = checkup(token);

        if (checkup == null) {
            return new CommonResult<>(200, "用户未登录或登录状态失效", null);
        }

        Integer paperId = Integer.parseInt(map.get("paperId").toString());

        Paper paper = paperService.getPaper(paperId);

        if (paper!=null){
            return new CommonResult<>(100, "查找成功",paper);
        }else {
            return new CommonResult<>(200, "失败成功",null);
        }
    }

    @RequestMapping("/getAllPaper")
    public CommonResult<List<Paper>> getAllPaper(@RequestBody HashMap<String, Object> map){
        String token = (String)map.get("token");
        String checkup = checkup(token);

        if (checkup == null) {
            return new CommonResult<>(200, "用户未登录或登录状态失效", null);
        }


        List<Paper> paperList = paperService.getAllPaper();

        if (paperList!=null){
            return new CommonResult<>(100, "查找成功",paperList);
        }else {
            return new CommonResult<>(200, "失败成功",null);
        }
    }

}