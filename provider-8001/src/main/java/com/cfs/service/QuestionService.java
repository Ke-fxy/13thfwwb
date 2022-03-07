package com.cfs.service;

import com.cfs.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * @Author Ke
 * @Date 2022/3/6 21:44
 * @Description
 * @Version 1.0
 */
@Service
public class QuestionService {

    @Resource
    QuestionMapper questionMapper;

    public Integer addQuestion(Integer id,
                               String text,
                               String option1,
                               String option2,
                               String option3,
                               String option4,
                               String answer,
                               Integer createrId,
                               Timestamp createTime,
                               Integer chapterId,
                               Integer modularId,
                               Integer diffculyt) {

        Integer insert = questionMapper.insert(id,text, option1, option2, option3, option4, answer, createrId, createTime, chapterId, modularId, diffculyt);

        return insert;

    }
}