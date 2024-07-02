package cn.edu.bupt.sgms.mapper;

import cn.edu.bupt.sgms.entity.Admin;
import cn.edu.bupt.sgms.entity.Course;
import cn.edu.bupt.sgms.entity.Student;
import cn.edu.bupt.sgms.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    @Select("SELECT * FROM admin_info WHERE username = #{username} AND password = #{password}")
    Admin login(@Param("username") String username, @Param("password") String password);

    @Update("UPDATE admin_info SET token = #{token} WHERE username = #{username}")
    void updateToken(@Param("username") String username, @Param("token") String token);

    @Select("SELECT * FROM admin_info WHERE token = #{token}")
    Admin getAdminInfo(@Param("token") String token);

    @Select("SELECT * FROM student_info WHERE student_id = #{studentId}")
    Student getStudentInfo(Integer studentId);

    @Select("SELECT * FROM teacher_info WHERE teacher_id = #{teacherId}")
    Teacher getTeacherInfo(Integer teacherId);

    @Update("UPDATE student_info SET username = #{username}, " +
            "student_name = #{studentName}, " +
            "sex = #{sex}, " +
            "class_id = #{classId}, " +
            "birthdate = #{birthdate}, " +
            "contact_info = #{contactInfo}, " +
            "address = #{address} WHERE student_id = #{studentId}")
    void updateStudentInfo(Student student);

    @Update("UPDATE teacher_info SET username = #{username}, " +
            "teacher_name = #{teacherName}, " +
            "sex = #{sex} WHERE teacher_id = #{teacherId}")
    void updateTeacherInfo(Teacher newTeacher);

    @Delete("DELETE FROM student_course WHERE student_id = #{studentId}")
    void deleteStudentCourses(Integer studentId);

    @Delete("DELETE FROM student_info WHERE student_id = #{studentId}")
    void deleteStudent(Integer studentId);

    @Delete("DELETE FROM course_info WHERE teacher_id = #{teacherId}")
    void deleteCoursesByTeacher(Integer teacherId);

    @Delete("DELETE FROM student_course WHERE course_id IN (SELECT course_id FROM course_info WHERE teacher_id = #{teacherId})")
    void deleteStudentCoursesByTeacher(Integer teacherId);

    @Delete("DELETE FROM teacher_info WHERE teacher_id = #{teacherId}")
    void deleteTeacher(Integer teacherId);

    @Select("SELECT * FROM course_info WHERE course_id = #{courseId}")
    Course getCourseInfo(Integer courseId);

    @Update("UPDATE course_info SET course_name = #{courseName}, " +
            "teacher_id = #{teacherId}, " +
            "course_no = #{courseNo} " +
            "WHERE course_id = #{courseId}")
    void updateCourseInfo(Course course);

    @Delete("DELETE FROM student_course WHERE course_id = #{courseId}")
    void deleteStudentCoursesByCourse(Integer courseId);

    @Delete("DELETE FROM course_info WHERE course_id = #{courseId}")
    void deleteCourse(Integer courseId);

    @Select("SELECT * FROM student_info")
    List<Student> getAllStudentInfo();

    @Select("SELECT * FROM teacher_info")
    List<Teacher> getAllTeacherInfo();

    @Select("SELECT * FROM course_info")
    List<Course> getAllCourseInfo();

    @Update("UPDATE admin_info SET token = NULL WHERE token = #{token}")
    void deleteToken(String token);

    @Update("UPDATE student_info SET password = #{password} WHERE student_id = #{studentId}")
    void resetStudentPassword(@Param("studentId")Integer studentId, @Param("password")String password);

    @Update("UPDATE teacher_info SET password = #{password} WHERE teacher_id = #{teacherId}")
    void resetTeacherPassword(@Param("teacherId")Integer teacherId, @Param("password")String password);

    @Update("UPDATE teacher_info SET token = NULL WHERE teacher_id = #{teacherId}")
    void deleteTeacherToken(Integer teacherId);

    @Update("UPDATE student_info SET token = NULL WHERE student_id = #{studentId}")
    void deleteStudentToken(Integer studentId);
}
