package com.cfs.controller;

import com.cfs.entities.Chapter;
import com.cfs.entities.CommonResult;
import com.cfs.entities.Course;
import com.cfs.entities.Modular;
import com.cfs.service.CourseService;
import com.cfs.util.JavaWebToken;
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
import java.util.Objects;

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
    public CommonResult<String> addCourse(@RequestBody HashMap<String, String> map) {

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

        Integer createrId = Integer.parseInt(checkup);

        boolean b = courseService.addCourse(number, credit, type, courseName, mode, createrId);

        return b ? new CommonResult<>(100, "添加成功") : new CommonResult<>(200, "添加失败");

    }

    @PostMapping(value = "/getAllCourses")
    public CommonResult<List<Course>> getAllCourses(@RequestBody HashMap<String, String> map) {

        String token = map.get("token");
        String checkup = checkup(token);

        if (checkup == null) {
            return new CommonResult<>(200, "用户未登录或登录状态失效", null);
        }

        List<Course> allCourses = courseService.getAllCourses();

        if (allCourses != null && allCourses.size() != 0) {
            return new CommonResult<>(100, "查询成功", allCourses);
        }

        return new CommonResult<>(200, "查询失败");
    }

    @PostMapping(value = "/getCourse")
    public CommonResult<Course> getCourse(@RequestBody HashMap<String, String> map) {

        String token = map.get("token");
        String checkup = checkup(token);

        if (checkup == null) {
            return new CommonResult<>(200, "用户未登录或登录状态失效", null);
        }

        Integer courseId = Integer.parseInt(map.get("courseId"));
        Course course = courseService.getCourse(courseId);

        if (course != null) {
            return new CommonResult<>(100, "查询成功", course);
        } else {
            return new CommonResult<>(200, "没有找到该课程");
        }

    }

    @PostMapping(value = "/addCourseToStu")
    public CommonResult<String> addCourseToStu(@RequestBody HashMap<String, String> map) {

        String token = map.get("token");
        String checkup = checkup(token);

        if (checkup == null) {
            return new CommonResult<>(200, "用户未登录或登录状态失效", null);
        }

        Integer courseId = Integer.parseInt(map.get("courseId"));
        Integer stuId = Integer.parseInt(map.get("stuId"));
        Integer teaId = Integer.parseInt(map.get("teaId"));

        boolean b = courseService.addCourseToStu(courseId, stuId, teaId);

        if (b) {
            return new CommonResult<>(100, "添加成功");
        } else {
            return new CommonResult<>(200, "添加失败");
        }

    }

    @PostMapping(value = "/addModular")
    public CommonResult<String> addModular(@RequestBody HashMap<String, String> map) {

        String token = map.get("token");
        String checkup = checkup(token);

        if (checkup == null) {
            return new CommonResult<>(200, "用户未登录或登录状态失效", null);
        }

        Integer courseId = Integer.parseInt(map.get("courseId"));
        String modularName = map.get("modularName");
        boolean b = courseService.addModular(courseId, modularName);

        return b ? new CommonResult<>(100, "添加成功") : new CommonResult<>(200, "添加失败");

    }

    @PostMapping(value = "/addChapter")
    public CommonResult<String> addChapter(@RequestBody HashMap<String, String> map) {

        String token = map.get("token");
        String checkup = checkup(token);

        if (checkup == null) {
            return new CommonResult<>(200, "用户未登录或登录状态失效", null);
        }

        Integer courseId = Integer.parseInt(map.get("courseId"));
        String chapName = map.get("chapName");
        boolean b = courseService.addChapter(courseId, chapName);

        return b ? new CommonResult<>(100, "添加成功") : new CommonResult<>(200, "添加失败");

    }

    @PostMapping(value = "/getChapters")
    public CommonResult<List<Chapter>> getChapters(@RequestBody HashMap<String, String> map) {

        String token = map.get("token");
        String checkup = checkup(token);

        if (checkup == null) {
            return new CommonResult<>(200, "用户未登录或登录状态失效", null);
        }

        Integer courseId = Integer.parseInt(map.get("courseId"));

        List<Chapter> allChapters = courseService.getAllChapters(courseId);

        if (allChapters != null) {
            return new CommonResult<>(100, "查询成功", allChapters);
        } else {
            return new CommonResult<>(200, "查询失败,该课程没有章节");
        }

    }

    @PostMapping(value = "/getModularise")
    public CommonResult<List<Modular>> getModularise(@RequestBody HashMap<String, String> map) {

        String token = map.get("token");
        String checkup = checkup(token);

        if (checkup == null) {
            return new CommonResult<>(200, "用户未登录或登录状态失效", null);
        }

        Integer courseId = Integer.parseInt(map.get("courseId"));

        List<Modular> allModularise = courseService.getAllModularise(courseId);

        if (allModularise != null) {
            return new CommonResult<>(100, "查询成功", allModularise);
        } else {
            return new CommonResult<>(200, "查询失败,该课程没有模块");
        }

    }


    @PostMapping(value = "/deleteChapter")
    public CommonResult<String> deleteChapter(@RequestBody HashMap<String, String> map) {

        String token = map.get("token");
        String checkup = checkup(token);

        if (checkup == null) {
            return new CommonResult<>(200, "用户未登录或登录状态失效", null);
        }

        Integer courseId = Integer.parseInt(map.get("courseId"));
        String chapName = map.get("chapName");

        Integer result = courseService.deleteChapter(courseId, chapName);


        return result > 0 ? new CommonResult<>(100, "删除成功") : new CommonResult<>(200, "删除失败");

    }

    @PostMapping(value = "/deleteModular")
    public CommonResult<String> deleteModular(@RequestBody HashMap<String, String> map) {

        String token = map.get("token");
        String checkup = checkup(token);

        if (checkup == null) {
            return new CommonResult<>(200, "用户未登录或登录状态失效", null);
        }

        Integer courseId = Integer.parseInt(map.get("courseId"));
        String modularName = map.get("modularName");

        Integer result = courseService.deleteModular(courseId, modularName);


        return result > 0 ? new CommonResult<>(100, "删除成功") : new CommonResult<>(200, "删除失败");

    }


    @PostMapping(value = "/updateCourse")
    public CommonResult<String> updateCourse(@RequestBody HashMap<String, String> map) {

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

        boolean b = courseService.updateCourse(id, number, credit, type, courseName, mode);

        return b ? new CommonResult<>(100, "修改成功") : new CommonResult<>(200, "修改失败");

    }

    @PostMapping(value = "/updateModular")
    public CommonResult<String> updateModular(@RequestBody HashMap<String, String> map) {

        String token = map.get("token");
        String checkup = checkup(token);

        if (checkup == null) {
            return new CommonResult<>(200, "用户未登录或登录状态失效", null);
        }

        Integer modularId = Integer.parseInt(map.get("modularId"));
        String modularName = map.get("modularName");
        boolean updateModular = courseService.updateModular(modularId, modularName);

        if (updateModular) {
            return new CommonResult<>(100, "更新成功");
        } else {
            return new CommonResult<>(200, "更新失败");
        }

    }

    @PostMapping(value = "/updateChapter")
    public CommonResult<String> updateChapter(@RequestBody HashMap<String, String> map) {

        String token = map.get("token");
        String checkup = checkup(token);

        if (checkup == null) {
            return new CommonResult<>(200, "用户未登录或登录状态失效", null);
        }

        Integer chapterId = Integer.parseInt(map.get("chapterId"));
        String chapterName = map.get("chapterName");
        boolean updateModular = courseService.updateChapter(chapterId, chapterName);

        if (updateModular) {
            return new CommonResult<>(100, "更新成功");
        } else {
            return new CommonResult<>(200, "更新失败");
        }

    }

}