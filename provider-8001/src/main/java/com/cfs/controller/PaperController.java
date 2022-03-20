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

        List<Map> questionList = (List<Map>) map.get("questionList");

        Paper paper = new Paper(null,paperName,maxMark,creatorId,courseId,createTime,change);
        Integer result = paperService.addPaper(paper,questionList);

        return result > 0 ? new CommonResult<>(100, "添加成功") : new CommonResult<>(200, "添加失败");
    }



}
