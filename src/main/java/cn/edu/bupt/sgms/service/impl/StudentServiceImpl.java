package cn.edu.bupt.sgms.service.impl;

import cn.edu.bupt.sgms.DTO.CourseScore;
import cn.edu.bupt.sgms.DTO.StudentInfo;
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
    public StudentInfo getStudentInfo(String token) {
        Student student = studentMapper.getStudentInfo(token);
        if (student == null) {
            throw new RuntimeException("Invalid token.");
        }
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setUsername(student.getUsername());
        studentInfo.setStudentName(student.getStudentName());
        studentInfo.setSex(student.getSex());
        studentInfo.setClassId(student.getClassId());
        studentInfo.setBirthdate(student.getBirthdate());
        studentInfo.setContactInfo(student.getContactInfo());
        studentInfo.setAddress(student.getAddress());
        return studentInfo;
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

    @Override
    public void changePassword(String token, String password) {
        Student student = studentMapper.getStudentInfo(token);
        if (student == null) {
            throw new RuntimeException("Invalid token.");
        }
        studentMapper.deleteToken(token);
        // 假设changePassword方法接受两个参数：学生ID和新密码
        studentMapper.changePassword(student.getStudentId(), password);
    }
}

