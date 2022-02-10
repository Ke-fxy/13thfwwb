package com.cfs.service;

import com.cfs.entities.Student;
import com.cfs.entities.StudentExample;
import com.cfs.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Ke
 * @Date 2022/2/10 12:51
 * @Description
 * @Version 1.0
 */
@Service
public class StudentService {

    @Resource
    StudentMapper studentMapper;

    public Student login(Integer sNo, String password) {

        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andSnoEqualTo(sNo);
        criteria.andPasswordEqualTo(password);
        List<Student> students = studentMapper.selectByExample(studentExample);
        Student student = students.get(0);

        return student;
    }
}