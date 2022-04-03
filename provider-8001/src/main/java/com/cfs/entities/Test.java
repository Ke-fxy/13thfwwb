package com.cfs.entities;

import lombok.*;
import org.bouncycastle.util.Times;

import java.sql.Timestamp;

/**
 * @Author Ke
 * @Date 2022/3/24 20:14
 * @Description
 * @Version 1.0
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Test {

    private Integer id;

    private String testName;

    private Integer createrId;

    private Timestamp createTime;

    private Timestamp beginTime;

    private Integer limitTime;

    private Integer time;

    private String place;

    private Integer courseId;

    private Integer examinerId;

    private Integer examine;

    private Timestamp examineTime;

    private Integer state;

    private Integer invigilatorId;

    private Integer online;
}