package cn.edu.bupt.sgms.DTO;

import lombok.Data;

@Data
public class CourseInfo {
    private String courseNo; // 课程号
    private String courseName; // 课程名
    private Integer teacherId; // 教师ID
}
