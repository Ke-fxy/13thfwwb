package com.cfs.controller;

import com.cfs.entities.Chapter;
import com.cfs.entities.CommonResult;
import com.cfs.entities.Course;
import com.cfs.entities.Modular;
import com.cfs.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Ke
 * @Date 2022/3/13 20:23
 * @Description
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping(value = "/course")
@CrossOrigin(value = "*",maxAge = 3600)
public class CourseController {

    @Resource
    CourseService courseService;

    @PostMapping(value = "/addCourse")
    public CommonResult<String> addCourse(@RequestBody HashMap<String,String> map){

        return courseService.addCourse(map);

    }

    @PostMapping(value = "/getAllCourses")
    public CommonResult<List<Course>> getAllCourses(@RequestBody HashMap<String, String> map) {

        return courseService.getAllCourses(map);

    }

    @PostMapping(value = "/getCourse")
    public CommonResult<Course> getCourse(@RequestBody HashMap<String, String> map) {

        return courseService.getCourse(map);

    }

    @PostMapping(value = "/addCourseToStu")
    public CommonResult<String> addCourseToStu(@RequestBody HashMap<String, String> map) {

        return courseService.addCourseToStu(map);

    }

    @PostMapping(value = "/addModular")
    public CommonResult<String> addModular(@RequestBody HashMap<String, String> map) {

        return courseService.addModular(map);

    }

    @PostMapping(value = "/addChapter")
    public CommonResult<String> addChapter(@RequestBody HashMap<String, String> map) {

        return courseService.addChapter(map);

    }

    @PostMapping(value = "/getChapters")
    public CommonResult<List<Chapter>> getChapters(@RequestBody HashMap<String, String> map) {

        return courseService.getChapters(map);

    }

    @PostMapping(value = "/getModularise")
    public CommonResult<List<Modular>> getModularise(@RequestBody HashMap<String, String> map) {

        return courseService.getModularise(map);

    }


    @PostMapping(value = "/deleteChapter")
    public CommonResult<String> deleteChapter(@RequestBody HashMap<String,String> map){

        return courseService.deleteChapter(map);

    }

    @PostMapping(value = "/deleteModular")
    public CommonResult<String> deleteModular(@RequestBody HashMap<String,String> map){

        return courseService.deleteModular(map);

    }


    @PostMapping(value = "/updateCourse")
    public CommonResult<String> updateCourse(@RequestBody HashMap<String,String> map){

        return courseService.updateCourse(map);

    }

    @PostMapping(value = "/updateModular")
    public CommonResult<String> updateModular(@RequestBody HashMap<String,String> map){

        return courseService.updateModular(map);

    }

    @PostMapping(value = "/updateChapter")
    public CommonResult<String> updateChapter(@RequestBody HashMap<String,String> map){

        return courseService.updateChapter(map);

    }

    @PostMapping(value = "/addCourseWithCM")
    public CommonResult<String> addCourseWithCM(@RequestBody HashMap<String, Object> map){

        return courseService.addCourseWithCM(map);

    }

    @PostMapping(value = "/getCourses")
    public CommonResult<Object> getCourses(@RequestBody HashMap<String, String> map){

        return courseService.getCourses(map);

    }

}