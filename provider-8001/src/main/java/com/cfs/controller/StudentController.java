package com.cfs.controller;

import com.cfs.entities.CommonResult;
import com.cfs.entities.Student;
import com.cfs.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author Ke
 * @Date 2022/2/9 21:56
 * @Description
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping("/student")
public class StudentController {

    @Resource
    StudentService studentService;

    @GetMapping (value = "/login")
    public CommonResult<Student> login(@RequestParam("sNo") Integer sNo,
                                       @RequestParam("password") String password){
        Student student = studentService.login(sNo, password);
        System.out.println("fxy");
        return new CommonResult(100,"无消息",student);
    }

}