package com.cfs.controller;

import com.cfs.entities.CommonResult;
import com.cfs.entities.Course;
import com.cfs.service.CourseService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Ke
 * @Date 2022/3/9 23:10
 * @Description
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping(value = "/course")
public class CourseController {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    CourseService courseService;

    private String checkup(String token) {

        ValueOperations<String, String> forValue = stringRedisTemplate.opsForValue();
        String s = forValue.get("userToken:" + token);
        if (s == null || s.length() == 0) {
            return null;
        }
        return s;

    }

    @PostMapping(value = "/addCourse")
    public CommonResult<String> addCourse(@RequestBody HashMap<String,String> map){

        String token = map.get("token");
        String checkup = checkup(token);

        if (checkup == null) {
            return new CommonResult<>(200, "用户未登录或登录状态失效", null);
        }

        String number = map.get("number");
        Integer credit = Integer.parseInt(map.get("credit"));
        String type = map.get("type");
        String courseName = map.get("courseName");
        Integer mode = Integer.parseInt(map.get("mode"));
        Integer createrId = Integer.parseInt(map.get("createrId"));

        boolean b = courseService.addCourse(number, credit, type, courseName, mode, createrId);

        return b?new CommonResult<>(100,"添加成功"):new CommonResult<>(200,"添加失败");

    }

    @PostMapping(value = "/getAllCourses")
    public CommonResult<List<Course>> getAllCourses(@RequestBody HashMap<String,String> map){

        String token = map.get("token");
        String checkup = checkup(token);

        if (checkup == null) {
            return new CommonResult<>(200, "用户未登录或登录状态失效", null);
        }

        List<Course> allCourses = courseService.getAllCourses();

        if (allCourses!=null&&allCourses.size()!=0){
            return new CommonResult<>(100,"查询成功",allCourses);
        }

        return new CommonResult<>(200,"查询失败");
    }

    @PostMapping(value = "/updateCourse")
    public CommonResult<String> updateCourse(@RequestBody HashMap<String,String> map){

        String token = map.get("token");
        String checkup = checkup(token);

        if (checkup == null) {
            return new CommonResult<>(200, "用户未登录或登录状态失效", null);
        }

        Integer id = Integer.parseInt(map.get("id"));
        String number = map.get("number");
        Integer credit = Integer.parseInt(map.get("credit"));
        String type = map.get("type");
        String courseName = map.get("courseName");
        Integer mode = Integer.parseInt(map.get("mode"));

        boolean b = courseService.updateCourse(id,number, credit, type, courseName, mode);

        return b?new CommonResult<>(100,"修改成功"):new CommonResult<>(200,"修改失败");

    }

}