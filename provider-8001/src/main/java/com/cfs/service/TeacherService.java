package com.cfs.service;

import com.cfs.entities.CommonResult;
import com.cfs.entities.Teacher;
import com.cfs.mapper.TeacherMapper;
import com.cfs.util.JavaWebToken;
import com.cfs.util.RSAEncrypt;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Ke
 * @Date 2022/3/13 19:32
 * @Description
 * @Version 1.0
 */
@Service
public class TeacherService {

    @Resource
    TeacherMapper teacherMapper;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    public String login(Integer tNo, String password) {

        Teacher teacher = teacherMapper.login(tNo);

        if (teacher != null) {
            String decrypt = "";

            try {
                decrypt = RSAEncrypt.decrypt(teacher.getPassword());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

            if (decrypt.equals(password)) {
                HashMap<String, Object> map = new HashMap<>(2);
                map.put("id", teacher.getId());
                String token = JavaWebToken.createJavaWebToken(map);

                ValueOperations<String, String> forValue = stringRedisTemplate.opsForValue();
                forValue.set("userToken:" + token, String.valueOf(teacher.getId()));
                //stringRedisTemplate.expire("userToken:" + token, 7, TimeUnit.DAYS);
                return token;
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    public Teacher getTeacher(Integer id) {

        Teacher teacher = teacherMapper.getTeacherById(id);

        if (teacher != null) {
            return teacher;
        } else {
            return null;
        }

    }

    public List<Teacher> getTeachers() {

        List<Teacher> teachers = teacherMapper.getTeachers();

        if (teachers != null && teachers.size() != 0) {
            return teachers;
        } else {
            return null;
        }

    }
}