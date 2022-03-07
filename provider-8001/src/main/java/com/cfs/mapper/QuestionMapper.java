package com.cfs.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * @Author Ke
 * @Date 2022/3/6 21:52
 * @Description
 * @Version 1.0
 */
@Mapper
public interface QuestionMapper {
    /**
     * 新增记录
     * @param text
     * @param option1
     * @param option2
     * @param option3
     * @param option4
     * @param answer
     * @param createrId
     * @param createTime
     * @param chapterId
     * @param modularId
     * @param diffculyt
     * @return
     */
    Integer insert(@Param("id") Integer id,
                   @Param("text") String text,
                   @Param("option1")String option1,
                   @Param("option2")String option2,
                   @Param("option3")String option3,
                   @Param("option4")String option4,
                   @Param("answer")String answer,
                   @Param("createrId")Integer createrId,
                   @Param("createTime") Timestamp createTime,
                   @Param("chapterId")Integer chapterId,
                   @Param("modularId")Integer modularId,
                   @Param("diffculyt")Integer diffculyt);
}
