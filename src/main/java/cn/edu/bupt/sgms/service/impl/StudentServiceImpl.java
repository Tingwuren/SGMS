package cn.edu.bupt.sgms.service.impl;

import cn.edu.bupt.sgms.DTO.CourseScore;
import cn.edu.bupt.sgms.entity.Student;
import cn.edu.bupt.sgms.mapper.StudentMapper;
import cn.edu.bupt.sgms.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public String login(String username, String password) {
        Student student = studentMapper.login(username, password);
        if (student == null) {
            throw new RuntimeException("Incorrect username or password.");
        }
        String token = UUID.randomUUID().toString();
        student.setToken(token);
        // 假设updateToken方法接受两个参数：用户名和令牌
        studentMapper.updateToken(username, token);
        return token;
    }

    @Override
    public void logout(String token) {
        // 假设deleteToken方法接受一个参数：令牌
        studentMapper.deleteToken(token);
    }

    @Override
    public Student getStudentInfo(String token) {
        Student student = studentMapper.getStudentInfo(token);
        if (student == null) {
            throw new RuntimeException("Invalid token.");
        }
        return student;
    }

    @Override
    public List<CourseScore> getStudentScore(String token) {
        Student student = studentMapper.getStudentInfo(token);
        if (student == null) {
            throw new RuntimeException("Invalid token.");
        }
        // 假设getStudentScore方法接受一个参数：学生ID
        return studentMapper.getStudentScore(student.getStudentId());
    }
}

