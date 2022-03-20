package com.cfs.service;

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

    public Integer addPaper(String paperName, Integer maxMark, Integer creatorId, Integer courseId, Timestamp createTime, Integer change){
        return paperMapper.addPaper(paperName, maxMark, creatorId, courseId,createTime, change);
    }

    public Integer addPaperQuestion(List<Map> questionList){


        ArrayList<Map> publicScList = new ArrayList<>();
        ArrayList<Map> publicCompList = new ArrayList<>();
        Iterator<Map> iterator = questionList.iterator();

        while (iterator.hasNext()){
            String questionType = iterator.next().get("questionType").toString();
            String privateType = iterator.next().get("private").toString();
//            System.out.println(iterator.next());
            if (questionType.equals("choice")&&privateType.equals("0")){
                publicScList.add(iterator.next());
                paperMapper.addPaperPublicSc(
                        Integer.parseInt(iterator.next().get("questionId").toString()),
                        Integer.parseInt(iterator.next().get("mark").toString()),
                        Integer.parseInt(iterator.next().get("index").toString()));
            }else if(questionType.equals("completion")&&privateType.equals("0")){
                publicCompList.add(iterator.next());
                paperMapper.addPaperPublicComp(
                        Integer.parseInt(iterator.next().get("questionId").toString()),
                        Integer.parseInt(iterator.next().get("mark").toString()),
                        Integer.parseInt(iterator.next().get("index").toString()));
            }
        }

        return 1;
    }
}
