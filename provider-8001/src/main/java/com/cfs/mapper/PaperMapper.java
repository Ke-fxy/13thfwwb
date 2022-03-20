package com.cfs.mapper;

import com.cfs.entities.Paper;
import com.cfs.entities.QuestionPublicComp;
import com.cfs.entities.QuestionPublicSc;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface PaperMapper {

    Integer addPaper(Paper paper);

    Integer addPaperPublicSc(
            @Param("id") Integer id,
            @Param("questionId") Integer questionId,
            @Param("mark") Integer mark,
            @Param("index") Integer index);

    Integer addPaperPublicComp(
            @Param("id") Integer id,
            @Param("questionId") Integer questionId,
            @Param("mark") Integer mark,
            @Param("index") Integer index);


}
