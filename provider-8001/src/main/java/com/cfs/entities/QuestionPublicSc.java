package com.cfs.entities;

import lombok.*;

import java.sql.Time;

/**
 * @Author Ke
 * @Date 2022/3/6 21:06
 * @Description
 * @Version 1.0
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuestionPublicSc {

    private Integer id;

    private String text;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

    private String answer;

    private Integer createrId;

    private Time createTime;

    private Integer chapterId;

    private Integer modularId;

    private Integer diffculyt;

    private Integer examine;

    private Integer examinerId;

    private Time examinerTime;

}