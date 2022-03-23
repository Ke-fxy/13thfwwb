package com.cfs.service;

import com.cfs.entities.QuestionPublicComp;
import com.cfs.entities.QuestionPublicCompWithName;
import com.cfs.entities.QuestionPublicSc;
import com.cfs.entities.QuestionPublicScWithName;
import com.cfs.mapper.QuestionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Author Ke
 * @Date 2022/3/6 21:44
 * @Description
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
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

    public List<QuestionPublicSc> getAllSC() {

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

    public Integer deleteQuestion(Integer id) {

        int result = questionMapper.delete(id);

        return result;

    }

    public Integer updateQuestion(Integer id,
                               String text,
                               String option1,
                               String option2,
                               String option3,
                               String option4,
                               String answer,
                               Integer chapterId,
                               Integer modularId,
                               Integer diffculyt) {

        Integer update = questionMapper.update(id,text, option1, option2, option3, option4, answer, chapterId, modularId, diffculyt);

        return update;

    }

    public boolean addQuestionComp(String content, String answer1, String answer2, String answer3, Integer createrId, Timestamp createTime, Integer chapterId, Integer modularId, Integer difficulty) {

        Integer integer = questionMapper.insertComp(content, answer1, answer2, answer3, createrId, createTime, chapterId, modularId, difficulty);

        if (integer!=0){
            return true;
        }else {
            return false;
        }

    }


    public Integer deleteComp(Integer id){
        return questionMapper.deleteComp(id);
    }

    public Integer updateComp(Integer id,String content, String answer1, String answer2, String answer3, Integer chapterId, Integer modularId, Integer difficulty){
        return questionMapper.updateComp(id, content, answer1, answer2, answer3, chapterId, modularId, difficulty);
    }

    public QuestionPublicComp getCompById(Integer id){
        return questionMapper.getComp(id);

    }

    public List<QuestionPublicComp> getAllComp(){
        return questionMapper.getAllComp();

    }

    public List<QuestionPublicSc> getQuestionScInCondition(Integer courseId, String type, Integer chapterId, Integer modularId, String content) {

        questionMapper.getQuestionScInCondition(courseId,chapterId,modularId,content);
        return null;
    }

    public List getAllQuestionInCondition(String type, Integer courseId, Integer chapterId, Integer modularId, String content) {

        List<QuestionPublicScWithName> questionScInCondition = null;
        List<QuestionPublicCompWithName> questionCompInCondition = null;

        if ("选择题".equals(type)){
            questionScInCondition = questionMapper.getQuestionScInCondition(courseId, chapterId, modularId, content);
            return questionScInCondition;
        }else {
            questionCompInCondition = questionMapper.getQuestionCompInCondition(courseId, chapterId, modularId, content);
            return questionCompInCondition;
        }

        /*if (map.size()!=0){
            return map;
        }else {
            return null;
        }*/

    }
    public List getAllQuestionInConditionWithName(String type, Integer courseId, Integer chapterId, Integer modularId, String content) {

        List<QuestionPublicScWithName> questionScInCondition = null;
        List<QuestionPublicCompWithName> questionCompInCondition = null;

        if ("选择题".equals(type)){
            questionScInCondition = questionMapper.getQuestionScInCondition(courseId, chapterId, modularId, content);
            /*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            for (QuestionPublicScWithName questionPublicScWithName:questionScInCondition){
                Timestamp createTime = questionPublicScWithName.getCreateTime();

            }*/
            return questionScInCondition;
        }else {
            questionCompInCondition = questionMapper.getQuestionCompInCondition(courseId, chapterId, modularId, content);
            return questionCompInCondition;
        }

        /*if (map.size()!=0){
            return map;
        }else {
            return null;
        }*/

    }
}