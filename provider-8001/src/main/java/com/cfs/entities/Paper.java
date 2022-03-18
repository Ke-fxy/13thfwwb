package com.cfs.entities;

import lombok.*;

import java.sql.Time;

/**
 * create by: syy
 * description: TODO
 * create time: 2022/3/18 14:21
 *
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Paper {

    private Integer id;

    private String paperName;

    private Integer maxMark;

    private Integer creatorId;

    private Integer courseId;

    private Time createTime;

    private Integer change;







}