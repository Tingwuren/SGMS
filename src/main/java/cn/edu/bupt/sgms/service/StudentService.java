package cn.edu.bupt.sgms.service;

import cn.edu.bupt.sgms.DTO.CourseScore;
import cn.edu.bupt.sgms.DTO.StudentInfo;
import cn.edu.bupt.sgms.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface StudentService extends IService<Student> {

    String login(String username, String password);

    StudentInfo getStudentInfo(String token);

    List<CourseScore> getStudentScore(String token);

    void logout(String token);

    void changePassword(String token, String password);
}

