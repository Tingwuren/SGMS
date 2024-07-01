package cn.edu.bupt.sgms.service;

import cn.edu.bupt.sgms.DTO.CourseInfo;
import cn.edu.bupt.sgms.DTO.StudentInfo;
import cn.edu.bupt.sgms.DTO.TeacherInfo;
import cn.edu.bupt.sgms.entity.Admin;
import cn.edu.bupt.sgms.entity.Student;
import cn.edu.bupt.sgms.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface AdminService extends IService<Admin> {
    String login(String username, String password);

    Admin getAdminInfo(String token);

    StudentInfo getStudentInfo(String token, Integer studentId);

    TeacherInfo getTeacherInfo(String token, Integer teacherId);

    void updateStudentInfo(String token, Integer studentId, StudentInfo studentInfo);

    void updateTeacherInfo(String token, Integer teacherId, TeacherInfo teacherInfo);

    void deleteStudent(String token, Integer studentId);

    void deleteTeacher(String token, Integer teacherId);

    CourseInfo getCourseInfo(String token, Integer courseId);

    void updateCourseInfo(String token, Integer courseId, CourseInfo courseInfo);

    void deleteCourse(String token, Integer courseId);

    List<StudentInfo> getAllStudentInfo(String token);

    List<TeacherInfo> getAllTeacherInfo(String token);

    List<CourseInfo> getAllCourseInfo(String token);

    void logout(String token);
}
