package cn.edu.bupt.sgms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("admin_info")
public class Admin {
    private Integer adminId; // 管理员ID
    private String username; // 管理员名
    private String password; // 管理员密码
    private String token; // 令牌
}
