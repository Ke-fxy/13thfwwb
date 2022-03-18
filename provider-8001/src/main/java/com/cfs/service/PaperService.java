package com.cfs.service;

import com.cfs.mapper.PaperMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Time;
import java.sql.Timestamp;

@Service
@Transactional(rollbackFor = Exception.class)
public class PaperService {

    @Resource
    PaperMapper paperMapper;

    public Integer addPaper(String paperName, Integer maxMark, Integer creatorId, Integer courseId, Timestamp createTime, Integer change){
        return paperMapper.addPaper(paperName, maxMark, creatorId, courseId,createTime, change);
    }
}
