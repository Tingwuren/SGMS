package cn.edu.bupt.sgms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("teacher_info")
public class Teacher {
    private Integer teacherId;
    private String username;
    private String password;
    private String teacherName;
    private String sex;
    private String token;
}
