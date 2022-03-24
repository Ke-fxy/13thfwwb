package com.cfs.mapper;

import com.cfs.entities.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    Integer deletePaper(@Param("id") Integer id);

    Integer updatePaper(Paper paper);

    Paper getPaper(@Param("id") Integer id);

    List<Paper> getAllPaper();


}
