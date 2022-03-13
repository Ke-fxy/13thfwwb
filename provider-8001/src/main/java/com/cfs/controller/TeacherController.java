package com.cfs.controller;

import com.cfs.entities.CommonResult;
import com.cfs.entities.Teacher;
import com.cfs.service.TeacherService;
import com.cfs.util.JavaWebToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Ke
 * @Date 2022/3/13 19:31
 * @Description
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping(value = "/teacher")
public class TeacherController {

    @Resource
    TeacherService teacherService;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    private String checkup(String token) {

        ValueOperations<String, String> forValue = stringRedisTemplate.opsForValue();
        String s = forValue.get("teacherToken:" + token);
        if (s == null || s.length() == 0) {
            return null;
        }
        return s;

    }

    @PostMapping(value = "/login")
    public CommonResult<String> login(@RequestBody HashMap<String,String> map){

        @NotNull
        Integer tNo = Integer.parseInt(map.get("tNo"));
        @NotNull
        String password = map.get("password");

        String token = teacherService.login(tNo, password);

        if (token!=null){
            return new CommonResult<>(100,"登录成功",token);
        }

        return new CommonResult<>(200,"登录失败");
    }

    @PostMapping(value = "getTeacher")
    public CommonResult<Teacher> getTeacher(@RequestBody HashMap<String,String> map){

        String token = map.get("token");
        String checkup = checkup(token);

        if (checkup == null) {
            return new CommonResult<>(200, "用户未登录或登录状态失效", null);
        }

        Map<String, Object> webToken = JavaWebToken.parserJavaWebToken(token);
        Integer id = (Integer) webToken.get("id");

        Teacher teacher = teacherService.getTeacher(id);

        return teacher!=null?new CommonResult<>(100,"查询成功",teacher):new CommonResult<>(200,"查询失败");

    }

}