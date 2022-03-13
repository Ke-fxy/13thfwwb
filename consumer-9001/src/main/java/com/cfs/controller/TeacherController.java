package com.cfs.controller;

import com.cfs.entities.CommonResult;
import com.cfs.entities.Teacher;
import com.cfs.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @Author Ke
 * @Date 2022/3/13 20:17
 * @Description
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping(value = "/teacher")
@CrossOrigin(value = "*",maxAge = 3600)
public class TeacherController {

    @Resource
    TeacherService teacherService;

    @PostMapping(value = "/login")
    public CommonResult<String> login(@RequestBody HashMap<String,String> map){

        return teacherService.login(map);

    }

    @PostMapping(value = "/getTeacher")
    public CommonResult<Teacher> getTeacher(@RequestBody HashMap<String,String> map){

        return teacherService.getTeacher(map);

    }

}