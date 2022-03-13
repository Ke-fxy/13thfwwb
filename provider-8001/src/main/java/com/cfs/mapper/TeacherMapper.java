package com.cfs.mapper;

import com.cfs.entities.Student;
import com.cfs.entities.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Ke
 * @Date 2022/3/13 19:32
 * @Description
 * @Version 1.0
 */
@Mapper
public interface TeacherMapper {
    /**
     * 通过tNo返回信息
     * @param tNo
     * @return
     */
    Teacher login(@Param("tNo") Integer tNo);

    /**
     * 通过id获得老师信息
     * @param id
     * @return
     */
    Teacher getTeacherById(@Param("id") Integer id);
}