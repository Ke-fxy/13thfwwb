package com.cfs.controller;

import com.cfs.entities.CommonResult;
import com.cfs.entities.Student;
import com.cfs.service.CodeService;
import com.cfs.service.StudentService;
import com.cfs.util.JavaWebToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

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


    @PostMapping(value = "/login")
    public CommonResult login(@RequestParam("sNo") Integer sNo,
                              @RequestParam("password") String password) {

        Map<String, Object> studentMap = studentService.login(sNo, password);
        if (studentMap == null) {
            return new CommonResult(200, "用户名不存在或密码错误");
        }
        Student student = (Student) studentMap.get("student");
        String token = (String) studentMap.get("token");

        if (student == null) {
            return new CommonResult(200, "用户名不存在或密码错误");
        } else {
            if (student.getPhone().length() == 0) {
                return new CommonResult(100, "未绑定手机号", token);
            } else {
                return new CommonResult(100, "登录成功", token);
            }
        }

    }


    @PostMapping(value = "/test")
    public String test(@RequestBody Map map){

        String str = (String) map.get("str");

        return str;

    }

    @PostMapping(value = "getCode")
    public CommonResult<String> getCode(@RequestBody Map map){

        String token = (String)map.get("token");
        String checkResult = studentService.checkup(token);

        if (checkResult==null){
            return new CommonResult(200,"用户未登录或登录状态失效");
        }

        String phone = (String) map.get("phone");

        String code = studentService.getCode(phone, checkResult);

        return new CommonResult(100,"?",code);
    }



}