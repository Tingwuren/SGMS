package cn.edu.bupt.sgms.service;

import cn.edu.bupt.sgms.DTO.CourseScore;
import cn.edu.bupt.sgms.DTO.TeacherInfo;
import cn.edu.bupt.sgms.entity.Course;
import cn.edu.bupt.sgms.entity.TakeCourse;
import cn.edu.bupt.sgms.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface TeacherService extends IService<Teacher> {
    String login(String username, String password);

    TeacherInfo getTeacherInfo(String token);

    List<Course> getTeacherCourse(String token);

    List<CourseScore> getTeacherScore(String token);

    void updateStudentScore(String token, TakeCourse takeCourse);

    void addStudent(String token, TakeCourse takeCourse);

    void deleteStudent(String token, TakeCourse takeCourse);

    void logout(String token);

    void updatePassword(String token, String password);
}
