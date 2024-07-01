package cn.edu.bupt.sgms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("student_course")
public class TakeCourse {
    private Integer studentId; // 学生ID
    private Integer courseId; // 课程ID
    private Integer score; // 分数
}
