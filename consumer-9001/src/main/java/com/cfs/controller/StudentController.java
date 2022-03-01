package com.cfs.controller;

import com.cfs.entities.CommonResult;
import com.cfs.entities.Student;
import com.cfs.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author Ke
 * @Date 2022/2/10 14:18
 * @Description
 * @Version 1.0
 */

@RestController
@Slf4j
@RequestMapping("/student")
public class StudentController {

    @Resource
    StudentService studentService;

    /*@GetMapping(value="/login")
    public CommonResult login(@RequestParam("sNo") Integer sNo,
                                       @RequestParam("password") String password){
        CommonResult studentCommonResult = studentService.login(sNo, password);
        System.out.println("syy");

        System.out.println("SYY");
        return studentCommonResult;
    }*/

    @PostMapping(value="/login")
    public CommonResult login(@RequestBody Map map){
        CommonResult studentCommonResult = studentService.login(map);
        return studentCommonResult;
    }

    @PostMapping(value = "/getCode")
    public CommonResult getCode(@RequestBody Map map){
        CommonResult commonResult = studentService.getCode(map);
        return commonResult;
    }

}