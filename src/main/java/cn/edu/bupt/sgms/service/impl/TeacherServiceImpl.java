package cn.edu.bupt.sgms.service.impl;

import cn.edu.bupt.sgms.DTO.CourseScore;
import cn.edu.bupt.sgms.entity.Course;
import cn.edu.bupt.sgms.entity.Student;
import cn.edu.bupt.sgms.entity.TakeCourse;
import cn.edu.bupt.sgms.entity.Teacher;
import cn.edu.bupt.sgms.mapper.TeacherMapper;
import cn.edu.bupt.sgms.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public String login(String username, String password) {
        Teacher teacher = teacherMapper.login(username, password);
        if (teacher == null) {
            throw new RuntimeException("Incorrect username or password.");
        }
        String token = UUID.randomUUID().toString();
        teacher.setToken(token);
        // 假设updateToken方法接受两个参数：用户名和令牌
        teacherMapper.updateToken(username, token);
        return token;
    }

    @Override
    public void logout(String token) {
        // 假设deleteToken方法接受一个参数：令牌
        teacherMapper.deleteToken(token);
    }

    @Override
    public Teacher getTeacherInfo(String token) {
        Teacher teacher = teacherMapper.getTeacherInfo(token);
        if (teacher == null) {
            throw new RuntimeException("Invalid token.");
        }
        return teacher;
    }

    @Override
    public List<Course> getTeacherCourse(String token) {
        Teacher teacher = teacherMapper.getTeacherInfo(token);
        if (teacher == null) {
            throw new RuntimeException("Invalid token.");
        }
        // 假设getTeacherCourse方法接受一个参数：教师ID
        return teacherMapper.getTeacherCourse(teacher.getTeacherId());
    }

    @Override
    public List<CourseScore> getTeacherScore(String token) {
        Teacher teacher = teacherMapper.getTeacherInfo(token);
        if (teacher == null) {
            throw new RuntimeException("Invalid token.");
        }
        // 假设getTeacherScore方法接受一个参数：教师ID
        return teacherMapper.getTeacherScore(teacher.getTeacherId());
    }

    @Override
    public void updateStudentScore(String token, TakeCourse takeCourse) {
        Teacher teacher = teacherMapper.getTeacherInfo(token);
        if (teacher == null) {
            throw new RuntimeException("Invalid token.");
        }
        boolean isTeacherCourse = teacherMapper.isTeacherCourse(teacher.getTeacherId(), takeCourse.getCourseId());
        if (!isTeacherCourse) {
            throw new RuntimeException("The teacher isn't teaching this course.");
        }
        boolean isStudentSelected = teacherMapper.isStudentSelected(takeCourse.getStudentId(), takeCourse.getCourseId());
        if (!isStudentSelected) {
            throw new RuntimeException("The student hasn't selected this course.");
        }
        teacherMapper.updateStudentScore(takeCourse);
    }

    @Override
    public void addStudent(String token, TakeCourse takeCourse) {
        Teacher teacher = teacherMapper.getTeacherInfo(token);
        if (teacher == null) {
            throw new RuntimeException("Invalid token.");
        }
        boolean isTeacherCourse = teacherMapper.isTeacherCourse(teacher.getTeacherId(), takeCourse.getCourseId());
        if (!isTeacherCourse) {
            throw new RuntimeException("The teacher isn't teaching this course.");
        }
        Student student = teacherMapper.getStudentInfo(takeCourse.getStudentId());
        if (student == null) {
            throw new RuntimeException("The student doesn't exist.");
        }
        boolean isStudentSelected = teacherMapper.isStudentSelected(takeCourse.getStudentId(), takeCourse.getCourseId());
        if (isStudentSelected) {
            throw new RuntimeException("The student has selected this course.");
        }
        teacherMapper.addStudent(takeCourse);
    }

    @Override
    public void deleteStudent(String token, TakeCourse takeCourse) {
        Teacher teacher = teacherMapper.getTeacherInfo(token);
        if (teacher == null) {
            throw new RuntimeException("Invalid token.");
        }
        boolean isTeacherCourse = teacherMapper.isTeacherCourse(teacher.getTeacherId(), takeCourse.getCourseId());
        if (!isTeacherCourse) {
            throw new RuntimeException("The teacher isn't teaching this course.");
        }
        boolean isStudentSelected = teacherMapper.isStudentSelected(takeCourse.getStudentId(), takeCourse.getCourseId());
        if (!isStudentSelected) {
            throw new RuntimeException("The student hasn't selected this course.");
        }
        teacherMapper.deleteStudent(takeCourse);
    }
}
