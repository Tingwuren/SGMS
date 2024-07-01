package cn.edu.bupt.sgms.DTO;

import lombok.Data;

@Data
public class CourseScore {
    private Integer courseId;
    private Integer studentId;
    private String studentName;
    private String courseNo;
    private String courseName;
    private Integer score;
}
