package com.cfs.mapper;

import com.cfs.entities.QuestionPublicComp;
import com.cfs.entities.QuestionPublicSc;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface PaperMapper {

    Integer addPaper(@Param("paperName") String paperName,
                     @Param("maxMark") Integer maxMark,
                     @Param("creatorId") Integer creatorId,
                     @Param("courseId") Integer courseId,
                     @Param("createTime") Timestamp createTime,
                     @Param("change") Integer change);

    Integer addPaperPublicSc(@Param("id") Integer id,List<QuestionPublicSc> publicScList);

    Integer addPaperPublicComp(@Param("id") Integer id,List<QuestionPublicComp> publicCompList);


}
