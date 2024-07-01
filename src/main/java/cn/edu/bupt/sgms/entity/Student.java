package cn.edu.bupt.sgms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

@Data
@TableName("student_info")
public class Student {
    @TableId
    private Integer studentId; // 主键，对应数据库的student_id
    private String username; // 用户名
    private String password; // 密码
    private Integer classId; // 对应数据库的class_id
    private String studentName; // 对应数据库的student_name
    private String sex; // 性别，如果使用枚举，需要额外处理映射
    private LocalDate birthdate; // 出生日期，使用Java 8的日期API
    private String contactInfo; // 对应数据库的contact_info
    private String address; // 地址
    private String token; // 令牌
}

