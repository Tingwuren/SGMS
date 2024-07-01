package cn.edu.bupt.sgms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("course_info")
public class Course {
    private Integer courseId; // 课程ID
    private String courseNo; // 课程号
    private String courseName; // 课程名
    private Integer teacherId; // 教师ID
}
