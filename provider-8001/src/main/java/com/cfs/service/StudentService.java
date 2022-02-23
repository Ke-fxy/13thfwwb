package com.cfs.service;

import com.cfs.entities.Student;
import com.cfs.entities.StudentExample;
import com.cfs.mapper.StudentMapper;
import com.cfs.util.CreateCode;
import com.cfs.util.JavaWebToken;
import com.cfs.util.RSAEncrypt;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    @Resource
    CodeService codeService;

    @Resource
    StringRedisTemplate stringRedisTemplate;


    public Map<String, Object> login(Integer sNo, String password) {

        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andSnoEqualTo(sNo);

        /*criteria.andPasswordEqualTo(password);*/
        List<Student> students = studentMapper.selectByExample(studentExample);

        if (students.size() == 0) {
            return null;
        }

        Student student = students.get(0);
        String decrypt = "";
        try {
            decrypt = RSAEncrypt.decrypt(student.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (student != null && password.equals(decrypt)) {
            Map<String, Object> map = new HashMap<>(2);
            map.put("id", student.getId());
            String token = JavaWebToken.createJavaWebToken(map);
            ValueOperations<String, String> forValue = stringRedisTemplate.opsForValue();
            forValue.set("userToken:" + token, String.valueOf(student.getId()));
            stringRedisTemplate.expire("userId:" + student.getId(), 7, TimeUnit.DAYS);
            Map<String, Object> studentMap = new HashMap<>();
            studentMap.put("student", student);
            studentMap.put("token", token);
            return studentMap;
        } else {
            return null;
        }
    }


    public String checkup(String token) {

        ValueOperations<String, String> forValue = stringRedisTemplate.opsForValue();
        String s = forValue.get("userToken:" + token);
        if (s==null||s.length()==0){
            return null;
        }
        return s;

    }

    public String getCode(String phone, String checkResult) {

        String code = CreateCode.getCode(6);
        String s = codeService.setCodeWithoutAddr(code, phone,checkResult);

        return s;
    }
}