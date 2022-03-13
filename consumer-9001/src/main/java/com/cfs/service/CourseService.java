package com.cfs.service;

import com.cfs.entities.Chapter;
import com.cfs.entities.CommonResult;
import com.cfs.entities.Course;
import com.cfs.entities.Modular;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;

/**
 * @Author Ke
 * @Date 2022/3/12 22:45
 * @Description
 * @Version 1.0
 */
@Service
@FeignClient(name = "nacos-provider")
public interface CourseService {

    @PostMapping(value = "/course/addCourse")
    public CommonResult<String> addCourse(@RequestBody HashMap<String,String> map);

    @PostMapping(value = "/course/getAllCourses")
    public CommonResult<List<Course>> getAllCourses(@RequestBody HashMap<String, String> map);

    @PostMapping(value = "/course/getCourse")
    public CommonResult<Course> getCourse(@RequestBody HashMap<String, String> map);

    @PostMapping(value = "/course/addCourseToStu")
    public CommonResult<String> addCourseToStu(@RequestBody HashMap<String, String> map);

    @PostMapping(value = "/course/addModular")
    public CommonResult<String> addModular(@RequestBody HashMap<String, String> map);

    @PostMapping(value = "/course/addChapter")
    public CommonResult<String> addChapter(@RequestBody HashMap<String, String> map);

    @PostMapping(value = "/course/getChapters")
    public CommonResult<List<Chapter>> getChapters(@RequestBody HashMap<String, String> map);

    @PostMapping(value = "/course/getModularise")
    public CommonResult<List<Modular>> getModularise(@RequestBody HashMap<String, String> map);

    @PostMapping(value = "/course/deleteChapter")
    public CommonResult<String> deleteChapter(@RequestBody HashMap<String,String> map);

    @PostMapping(value = "/course/updateCourse")
    public CommonResult<String> updateCourse(@RequestBody HashMap<String,String> map);

}
