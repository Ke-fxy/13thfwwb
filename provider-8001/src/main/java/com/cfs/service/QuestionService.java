package com.cfs.service;

import com.cfs.entities.QuestionPublicSc;
import com.cfs.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

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

        Integer insert = questionMapper.insert(id, text, option1, option2, option3, option4, answer, createrId, createTime, chapterId, modularId, diffculyt);

        return insert;

    }

    public QuestionPublicSc getQuestionById(Integer id) {

        QuestionPublicSc questionPublicSc = questionMapper.get(id);

        return questionPublicSc;

    }

    public List<QuestionPublicSc> getAllQuestion() {

        List<QuestionPublicSc> publicScs = questionMapper.getAll();

        return publicScs;

    }

    public boolean checkUser(Integer userId) {

        Integer role = questionMapper.getUserRoleById(userId);

        if (role != null && role == 1) {
            return true;
        } else {
            return false;
        }

    }

    public boolean checkUser2(Integer userId) {
        Integer role = questionMapper.getUserRoleById(userId);

        if (role != null && role == 2) {
            return true;
        } else {
            return false;
        }
    }

    public List<QuestionPublicSc> getQuestionWithoutExamine(Integer courseId) {

        List<QuestionPublicSc> quesWithoutExamByCId = questionMapper.getQuesWithoutExamByCId(courseId);

        return quesWithoutExamByCId;

    }

    public boolean examineQuestion(Integer userId, Integer questionId, Integer result) {

        Timestamp examineTime = new Timestamp(System.currentTimeMillis());

        Integer examine = questionMapper.updateExamine(userId, questionId, result, examineTime);

        if (examine == 1) {
            return true;
        } else {
            return false;
        }

    }
}