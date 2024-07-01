package cn.edu.bupt.sgms.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentInfo {
    private String username; // 用户名
    private Integer classId; // 对应数据库的class_id
    private String studentName; // 对应数据库的student_name
    private String sex; // 性别，如果使用枚举，需要额外处理映射
    private LocalDate birthdate; // 出生日期，使用Java 8的日期API
    private String contactInfo; // 对应数据库的contact_info
    private String address; // 地址
}
