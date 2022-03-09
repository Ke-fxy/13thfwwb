package com.cfs.service;

import com.cfs.entities.Course;
import com.cfs.mapper.CourseMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Ke
 * @Date 2022/3/9 23:11
 * @Description
 * @Version 1.0
 */
@Service
public class CourseService {

    @Resource
    CourseMapper courseMapper;

    public boolean addCourse(String number, Integer credit, String type, String courseName, Integer mode, Integer createrId) {

        Integer id = null;
        Integer add = courseMapper.add(id, number, credit, type, courseName, mode, createrId);

        if (add==1){
            return true;
        }else {
            return false;
        }

    }

    public List<Course> getAllCourses() {

        List<Course> courses = courseMapper.selectAll();

        return courses;

    }
}