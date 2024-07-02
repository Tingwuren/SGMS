package cn.edu.bupt.sgms.mapper;

import cn.edu.bupt.sgms.DTO.CourseScore;
import cn.edu.bupt.sgms.entity.Course;
import cn.edu.bupt.sgms.entity.Student;
import cn.edu.bupt.sgms.entity.TakeCourse;
import cn.edu.bupt.sgms.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {
    @Select("SELECT * FROM teacher_info WHERE username = #{username} AND password = #{password}")
    Teacher login(@Param("username") String username, @Param("password") String password);

    @Update("UPDATE teacher_info SET token = #{token} WHERE username = #{username}")
    void updateToken(@Param("username") String username, @Param("token") String token);

    @Select("SELECT * FROM teacher_info WHERE token = #{token}")
    Teacher getTeacherInfo(@Param("token") String token);

    @Select("SELECT * FROM course_info WHERE teacher_id = #{teacherId}")
    List<Course> getTeacherCourse(@Param("teacherId") Integer teacherId);

    @Select("SELECT ci.course_id, ci.course_no, ci.course_name, si.student_name, si.student_id, sc.score " +
            "FROM course_info ci " +
            "JOIN teacher_info ti ON ci.teacher_id = ti.teacher_id " +
            "JOIN student_course sc ON ci.course_id = sc.course_id " +
            "JOIN student_info si ON sc.student_id = si.student_id " +
            "WHERE ti.teacher_id = #{teacherId}")
    List<CourseScore> getTeacherScore(@Param("teacherId") Integer teacherId);

    @Select("SELECT COUNT(*) > 0 FROM course_info WHERE teacher_id = #{teacherId} AND course_id = #{courseId}")
    boolean isTeacherCourse(@Param("teacherId") Integer teacherId, @Param("courseId") Integer courseId);

    @Select("SELECT COUNT(*) > 0 FROM student_course WHERE student_id = #{studentId} AND course_id = #{courseId}")
    boolean isStudentSelected(@Param("studentId") Integer studentId, @Param("courseId") Integer courseId);

    @Update("UPDATE student_course SET score = #{score} WHERE student_id = #{studentId} AND course_id = #{courseId}")
    void updateStudentScore(TakeCourse takeCourse);

    @Update("INSERT INTO student_course (student_id, course_id, score) VALUES (#{studentId}, #{courseId}, #{score})")
    void addStudent(TakeCourse takeCourse);

    @Select("SELECT * FROM student_info WHERE student_id = #{studentId}")
    Student getStudentInfo(Integer studentId);

    @Update("DELETE FROM student_course WHERE student_id = #{studentId} AND course_id = #{courseId}")
    void deleteStudent(TakeCourse takeCourse);

    @Update("UPDATE teacher_info SET token = NULL WHERE token = #{token}")
    void deleteToken(String token);

    @Update("UPDATE teacher_info SET password = #{password} WHERE teacher_id = #{teacherId}")
    void changePassword(@Param("teacherId")Integer teacherId, @Param("password")String password);
}
