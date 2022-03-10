package com.cfs.service;

import com.cfs.entities.Chapter;
import com.cfs.entities.Course;
import com.cfs.entities.Modular;
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

        if (add == 1) {
            return true;
        } else {
            return false;
        }

    }

    public List<Course> getAllCourses() {

        List<Course> courses = courseMapper.selectAll();

        try {
            for (Course c : courses) {
                Integer courseId = c.getId();
                List<Chapter> chapters = courseMapper.getChapters(courseId);
                List<Modular> modulars = courseMapper.getModulars(courseId);
                c.setChapters(chapters);
                c.setModularise(modulars);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return courses;

    }

    public Course getCourse(Integer courseId) {

        Course course = courseMapper.getCourseById(courseId);
        List<Chapter> chapters = courseMapper.getChapters(courseId);
        List<Modular> modulars = courseMapper.getModulars(courseId);

        try {
            course.setChapters(chapters);
            course.setModularise(modulars);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return course;
    }

    public boolean addCourseToStu(Integer courseId, Integer stuId, Integer teaId) {

        Integer integer = null;

        try {
            integer = courseMapper.addCourseToStu(courseId, stuId, teaId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        if (integer == 1) {
            return true;
        } else {
            return false;
        }

    }

    public boolean addModular(Integer courseId, String modularName) {

        Integer integer = null;

        try {
            integer = courseMapper.addModular(courseId, modularName);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        if (integer == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addChapter(Integer courseId, String chapName) {

        Integer integer = null;

        try {
            integer = courseMapper.addChapter(courseId, chapName);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        if (integer == 1) {
            return true;
        } else {
            return false;
        }

    }

    public List<Chapter> getAllChapters(Integer courseId) {

        List<Chapter> chapters = courseMapper.getChapters(courseId);

        if (chapters != null && chapters.size() != 0) {
            return chapters;
        } else {
            return null;
        }

    }

    public List<Modular> getAllModularise(Integer courseId) {

        List<Modular> modularise = courseMapper.getModulars(courseId);

        if (modularise != null && modularise.size() != 0) {
            return modularise;
        } else {
            return null;
        }

    }
}