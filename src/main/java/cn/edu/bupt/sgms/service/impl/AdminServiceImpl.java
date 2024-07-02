package cn.edu.bupt.sgms.service.impl;

import cn.edu.bupt.sgms.DTO.CourseInfo;
import cn.edu.bupt.sgms.DTO.StudentInfo;
import cn.edu.bupt.sgms.DTO.TeacherInfo;
import cn.edu.bupt.sgms.entity.Admin;
import cn.edu.bupt.sgms.entity.Course;
import cn.edu.bupt.sgms.entity.Student;
import cn.edu.bupt.sgms.entity.Teacher;
import cn.edu.bupt.sgms.mapper.AdminMapper;
import cn.edu.bupt.sgms.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public String login(String username, String password) {
        Admin admin = adminMapper.login(username, password);
        if (admin == null) {
            throw new RuntimeException("Incorrect username or password.");
        }
        String token = UUID.randomUUID().toString();
        admin.setToken(token);
        adminMapper.updateToken(username, token);
        return token;
    }

    @Override
    public void logout(String token) {
        adminMapper.deleteToken(token);
    }

    @Override
    public Admin getAdminInfo(String token) {
        Admin admin = adminMapper.getAdminInfo(token);
        if (admin == null) {
            throw new RuntimeException("Invalid token.");
        }
        return admin;
    }

    @Override
    public StudentInfo getStudentInfo(String token, Integer studentId) {
        Admin admin = adminMapper.getAdminInfo(token);
        if (admin == null) {
            throw new RuntimeException("Invalid token.");
        }
        Student student = adminMapper.getStudentInfo(studentId);
        if (student == null) {
            throw new RuntimeException("Student not found.");
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
    public TeacherInfo getTeacherInfo(String token, Integer teacherId) {
        Admin admin = adminMapper.getAdminInfo(token);
        if (admin == null) {
            throw new RuntimeException("Invalid token.");
        }
        Teacher teacher = adminMapper.getTeacherInfo(teacherId);
        if (teacher == null) {
            throw new RuntimeException("Teacher not found.");
        }
        TeacherInfo teacherInfo = new TeacherInfo();
        teacherInfo.setUsername(teacher.getUsername());
        teacherInfo.setTeacherName(teacher.getTeacherName());
        teacherInfo.setSex(teacher.getSex());
        return teacherInfo;
    }

    @Override
    public void updateStudentInfo(String token, Integer studentId, StudentInfo studentInfo) {
        Admin admin = adminMapper.getAdminInfo(token);
        if (admin == null) {
            throw new RuntimeException("Invalid token.");
        }
        Student student = adminMapper.getStudentInfo(studentId);
        if (student == null) {
            throw new RuntimeException("Student not found.");
        }
        Student newStudent = new Student();
        newStudent.setStudentId(studentId);

        newStudent.setUsername(studentInfo.getUsername());
        newStudent.setStudentName(studentInfo.getStudentName());
        newStudent.setSex(studentInfo.getSex());
        newStudent.setClassId(studentInfo.getClassId());
        newStudent.setBirthdate(studentInfo.getBirthdate());
        newStudent.setContactInfo(studentInfo.getContactInfo());
        newStudent.setAddress(studentInfo.getAddress());
        adminMapper.updateStudentInfo(newStudent);
    }

    @Override
    public void updateTeacherInfo(String token, Integer teacherId, TeacherInfo teacherInfo) {
        Admin admin = adminMapper.getAdminInfo(token);
        if (admin == null) {
            throw new RuntimeException("Invalid token.");
        }
        Teacher teacher = adminMapper.getTeacherInfo(teacherId);
        if (teacher == null) {
            throw new RuntimeException("Teacher not found.");
        }
        Teacher newTeacher = new Teacher();
        newTeacher.setTeacherId(teacherId);

        newTeacher.setUsername(teacherInfo.getUsername());
        newTeacher.setTeacherName(teacherInfo.getTeacherName());
        newTeacher.setSex(teacherInfo.getSex());
        adminMapper.updateTeacherInfo(newTeacher);
    }

    @Override
    public void deleteStudent(String token, Integer studentId) {
        Admin admin = adminMapper.getAdminInfo(token);
        if (admin == null) {
            throw new RuntimeException("Invalid token.");
        }
        Student student = adminMapper.getStudentInfo(studentId);
        if (student == null) {
            throw new RuntimeException("Student not found.");
        }
        adminMapper.deleteStudentCourses(studentId);
        adminMapper.deleteStudent(studentId);
    }

    @Override
    public void deleteTeacher(String token, Integer teacherId) {
        Admin admin = adminMapper.getAdminInfo(token);
        if (admin == null) {
            throw new RuntimeException("Invalid token.");
        }
        Teacher teacher = adminMapper.getTeacherInfo(teacherId);
        if (teacher == null) {
            throw new RuntimeException("Teacher not found.");
        }
        adminMapper.deleteStudentCoursesByTeacher(teacherId);
        adminMapper.deleteCoursesByTeacher(teacherId);
        adminMapper.deleteTeacher(teacherId);
    }

    @Override
    public CourseInfo getCourseInfo(String token, Integer courseId) {
        Admin admin = adminMapper.getAdminInfo(token);
        if (admin == null) {
            throw new RuntimeException("Invalid token.");
        }
        Course course = adminMapper.getCourseInfo(courseId);
        if (course == null) {
            throw new RuntimeException("Course not found.");
        }
        CourseInfo courseInfo = new CourseInfo();
        courseInfo.setCourseNo(course.getCourseNo());
        courseInfo.setCourseName(course.getCourseName());
        courseInfo.setTeacherId(course.getTeacherId());
        return courseInfo;
    }

    @Override
    public void updateCourseInfo(String token, Integer courseId, CourseInfo courseInfo) {
        Admin admin = adminMapper.getAdminInfo(token);
        if (admin == null) {
            throw new RuntimeException("Invalid token.");
        }
        Course course = adminMapper.getCourseInfo(courseId);
        if (course == null) {
            throw new RuntimeException("Course not found.");
        }
        Course newCourse = new Course();
        newCourse.setCourseId(courseId);

        newCourse.setCourseNo(courseInfo.getCourseNo());
        newCourse.setCourseName(courseInfo.getCourseName());
        newCourse.setTeacherId(courseInfo.getTeacherId());
        adminMapper.updateCourseInfo(newCourse);
    }

    @Override
    public void deleteCourse(String token, Integer courseId) {
        Admin admin = adminMapper.getAdminInfo(token);
        if (admin == null) {
            throw new RuntimeException("Invalid token.");
        }
        Course course = adminMapper.getCourseInfo(courseId);
        if (course == null) {
            throw new RuntimeException("Course not found.");
        }
        adminMapper.deleteStudentCoursesByCourse(courseId);
        adminMapper.deleteCourse(courseId);
    }

    @Override
    public List<StudentInfo> getAllStudentInfo(String token) {
        Admin admin = adminMapper.getAdminInfo(token);
        if (admin == null) {
            throw new RuntimeException("Invalid token.");
        }
        List<StudentInfo> studentInfoList = new ArrayList<>();
        List<Student> studentList = adminMapper.getAllStudentInfo();
        for (Student student : studentList) {
            StudentInfo studentInfo = new StudentInfo();
            studentInfo.setUsername(student.getUsername());
            studentInfo.setStudentName(student.getStudentName());
            studentInfo.setSex(student.getSex());
            studentInfo.setClassId(student.getClassId());
            studentInfo.setBirthdate(student.getBirthdate());
            studentInfo.setContactInfo(student.getContactInfo());
            studentInfo.setAddress(student.getAddress());
            studentInfoList.add(studentInfo);
        }
        return studentInfoList;
    }

    @Override
    public List<TeacherInfo> getAllTeacherInfo(String token) {
        Admin admin = adminMapper.getAdminInfo(token);
        if (admin == null) {
            throw new RuntimeException("Invalid token.");
        }
        List<TeacherInfo> teacherInfoList = new ArrayList<>();
        List<Teacher> teacherList = adminMapper.getAllTeacherInfo();
        for (Teacher teacher : teacherList) {
            TeacherInfo teacherInfo = new TeacherInfo();
            teacherInfo.setUsername(teacher.getUsername());
            teacherInfo.setTeacherName(teacher.getTeacherName());
            teacherInfo.setSex(teacher.getSex());
            teacherInfoList.add(teacherInfo);
        }
        return teacherInfoList;
    }

    @Override
    public List<CourseInfo> getAllCourseInfo(String token) {
        Admin admin = adminMapper.getAdminInfo(token);
        if (admin == null) {
            throw new RuntimeException("Invalid token.");
        }
        List<CourseInfo> courseInfoList = new ArrayList<>();
        List<Course> courseList = adminMapper.getAllCourseInfo();
        for (Course course : courseList) {
            CourseInfo courseInfo = new CourseInfo();
            courseInfo.setCourseNo(course.getCourseNo());
            courseInfo.setCourseName(course.getCourseName());
            courseInfo.setTeacherId(course.getTeacherId());
            courseInfoList.add(courseInfo);
        }
        return courseInfoList;
    }

    @Override
    public void resetStudentPassword(String token, Integer studentId, String password) {
        Admin admin = adminMapper.getAdminInfo(token);
        if (admin == null) {
            throw new RuntimeException("Invalid token.");
        }
        Student student = adminMapper.getStudentInfo(studentId);
        if (student == null) {
            throw new RuntimeException("Student not found.");
        }
        adminMapper.deleteStudentToken(studentId);
        adminMapper.resetStudentPassword(studentId, password);
    }

    @Override
    public void resetTeacherPassword(String token, Integer teacherId, String password) {
        Admin admin = adminMapper.getAdminInfo(token);
        if (admin == null) {
            throw new RuntimeException("Invalid token.");
        }
        Teacher teacher = adminMapper.getTeacherInfo(teacherId);
        if (teacher == null) {
            throw new RuntimeException("Teacher not found.");
        }
        adminMapper.deleteTeacherToken(teacherId);
        adminMapper.resetTeacherPassword(teacherId, password);
    }
}
