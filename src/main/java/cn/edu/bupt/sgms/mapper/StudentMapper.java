package cn.edu.bupt.sgms.mapper;


import cn.edu.bupt.sgms.DTO.CourseScore;
import cn.edu.bupt.sgms.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    @Select("SELECT * FROM student_info WHERE username = #{username} AND password = #{password}")
    Student login(@Param("username") String username, @Param("password") String password);

    @Update("UPDATE student_info SET token = #{token} WHERE username = #{username}")
    void updateToken(@Param("username") String username, @Param("token") String token);

    @Select("SELECT * FROM student_info WHERE token = #{token}")
    Student getStudentInfo(@Param("token") String token);

    @Select("SELECT ci.course_id, ci.course_no, ci.course_name, sc.score, si.student_name,si.student_id " +
            "FROM student_course sc " +
            "JOIN course_info ci ON sc.course_id = ci.course_id " +
            "JOIN student_info si ON sc.student_id = si.student_id " +
            "WHERE sc.student_id = #{studentId}")
    List<CourseScore> getStudentScore(@Param("studentId") Integer studentId);

    @Update("UPDATE student_info SET token = NULL WHERE token = #{token}")
    void deleteToken(String token);
}

