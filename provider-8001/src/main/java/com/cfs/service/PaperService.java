package com.cfs.service;

import com.cfs.entities.Paper;
import com.cfs.mapper.PaperMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class PaperService {

    @Resource
    PaperMapper paperMapper;

    public Integer addPaper(Paper paper, List<Map> questionList) {

        Integer result = paperMapper.addPaper(paper);

        Integer paperId = paper.getId();

        for (int i = 0; i <questionList.size() ; i++) {
            String questionType = questionList.get(i).get("questionType").toString();
            String privateType = questionList.get(i).get("private").toString();

            if (questionType.equals("0") && privateType.equals("0")) {
                paperMapper.addPaperPublicSc(
                        paperId,
                        Integer.parseInt(questionList.get(i).get("questionId").toString()),
                        Integer.parseInt(questionList.get(i).get("mark").toString()),
                        Integer.parseInt(questionList.get(i).get("index").toString()));
            } else if (questionType.equals("1") && privateType.equals("0")) {
                paperMapper.addPaperPublicComp(
                        paperId,
                        Integer.parseInt(questionList.get(i).get("questionId").toString()),
                        Integer.parseInt(questionList.get(i).get("mark").toString()),
                        Integer.parseInt(questionList.get(i).get("index").toString()));
            }
        }
        return result;
    }

}
