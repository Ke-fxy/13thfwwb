package com.cfs.entities;

import lombok.*;

import java.sql.Time;

/**
 * @Author Ke
 * @Date 2022/3/16 21:17
 * @Description 填空题(系统)
 * @Version 1.0
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QuestionPublicComp {

    private Integer id;

    private String content;

    private String answer1;

    private String answer2;

    private String answer3;

    private Integer createrId;

    private Time createTime;

    private Integer examine;

    private Integer examineId;

    private Time examineTime;

    private Integer chapterId;

    private Integer modularId;

    private Integer difficulty;

}