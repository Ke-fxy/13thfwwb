package com.cfs.service;

import com.cfs.entities.CommonResult;
import com.cfs.entities.Teacher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;

/**
 * @Author Ke
 * @Date 2022/3/13 20:16
 * @Description
 * @Version 1.0
 */
@Service
@FeignClient(name = "nacos-provider")
public interface TeacherService {

    @PostMapping(value = "/teacher/login")
    public CommonResult<String> login(@RequestBody HashMap<String,String> map);

    @PostMapping(value = "/teacher/getTeacher")
    public CommonResult<Teacher> getTeacher(@RequestBody HashMap<String,String> map);

    @PostMapping(value = "/teacher/getTeachers")
    public CommonResult<List<Teacher>> getTeachers(@RequestBody HashMap<String,String> map);

}
