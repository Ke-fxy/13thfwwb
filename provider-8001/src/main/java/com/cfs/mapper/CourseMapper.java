package com.cfs.mapper;

import com.cfs.entities.Chapter;
import com.cfs.entities.Course;
import com.cfs.entities.Modular;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Ke
 * @Date 2022/3/9 23:11
 * @Description
 * @Version 1.0
 */
@Mapper
public interface CourseMapper {

    /**
     * 添加课程
     *
     * @param id
     * @param number
     * @param credit
     * @param type
     * @param courseName
     * @param mode
     * @param createrId
     * @return
     */
    Integer add(@Param("id") Integer id,
                @Param("number") String number,
                @Param("credit") Integer credit,
                @Param("type") String type,
                @Param("courseName") String courseName,
                @Param("mode") Integer mode,
                @Param("createrId") Integer createrId);


    /**
     * 获得全部课程信息
     *
     * @return
     */
    List<Course> selectAll();

    /**
     * 根据课程id获得章节
     *
     * @param courseId
     * @return
     */
    List<Chapter> getChapters(@Param("courseId") Integer courseId);

    /**
     * 根据课程id获得模块
     *
     * @param courseId
     * @return
     */
    List<Modular> getModulars(@Param("courseId") Integer courseId);

    /**
     * 根据上传的id获得课程
     *
     * @param courseId
     * @return
     */
    Course getCourseById(@Param("courseId") Integer courseId);

    /**
     * 根据courseId获得chapter
     *
     * @param courseId
     * @return
     */
    Chapter getChapter(@Param("courseId") Integer courseId);

    /**
     * 为课程学生老师表添加记录
     *
     * @param courseId
     * @param stuId
     * @param teaId
     * @return
     */
    Integer addCourseToStu(@Param("courseId") Integer courseId,
                           @Param("studentId") Integer stuId,
                           @Param("teacherId") Integer teaId);

    /**
     * 为课程添加模块
     *
     * @param courseId
     * @param modularName
     * @return
     */
    Integer addModular(@Param("courseId") Integer courseId,
                       @Param("modularName") String modularName);

    /**
     * 为课程添加章节
     *
     * @param courseId
     * @param chapName
     * @return
     */
    Integer addChapter(@Param("courseId") Integer courseId,
                       @Param("chapName") String chapName);
}