package cn.edu.bupt.sgms.controller;

import cn.edu.bupt.sgms.DTO.*;
import cn.edu.bupt.sgms.entity.Course;
import cn.edu.bupt.sgms.entity.Student;
import cn.edu.bupt.sgms.entity.TakeCourse;
import cn.edu.bupt.sgms.entity.Teacher;
import cn.edu.bupt.sgms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    // 教师登录
    @PostMapping("/login")
    public ResponseEntity<Response<String>> loginTeacher(@RequestBody LoginRequest loginRequest) {
        try {
            String token = teacherService.login(loginRequest.getUsername(), loginRequest.getPassword());
            return ResponseEntity.ok(new Response<>(200, "Login successful", token));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Response<>(400, e.getMessage(), null));
        }
    }

    // 教师注销
    @PostMapping("/logout")
    public ResponseEntity<Response<String>> logoutTeacher(@RequestHeader("Authorization") String authHeader) {
        try {
            // 通常Bearer Token是以"Bearer "为前缀，这里移除前缀获取真正的token
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                teacherService.logout(token);
                return ResponseEntity.ok(new Response<>(200, "Logout successful", null));
            } else {
                // 如果请求头不包含Bearer Token，则返回错误响应
                return ResponseEntity.badRequest().body(new Response<>(400, "No Bearer Token found", null));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Response<>(400, e.getMessage(), null));
        }
    }

    // 教师获取自己的基本信息
    @GetMapping("/info")
    public ResponseEntity<Response<TeacherInfo>> getStudentInfo(@RequestHeader("Authorization") String authHeader) {
        try {
            // 通常Bearer Token是以"Bearer "为前缀，这里移除前缀获取真正的token
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                TeacherInfo teacherInfo = teacherService.getTeacherInfo(token);
                return ResponseEntity.ok(new Response<>(200, "Get teacher info successful", teacherInfo));
            } else {
                // 如果请求头不包含Bearer Token，则返回错误响应
                return ResponseEntity.badRequest().body(new Response<>(400, "No Bearer Token found", null));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Response<>(400, e.getMessage(), null));
        }
    }

    // 教师查询所授课程的基本信息
    @GetMapping("/course")
    public ResponseEntity<Response<List<Course>>> getTeacherCourse(@RequestHeader("Authorization") String authHeader) {
        try {
            // 通常Bearer Token是以"Bearer "为前缀，这里移除前缀获取真正的token
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                // 假设getTeacherCourse方法接受一个参数：令牌
                List<Course> course = teacherService.getTeacherCourse(token);
                return ResponseEntity.ok(new Response<>(200, "Get teacher course successful", course));
            } else {
                // 如果请求头不包含Bearer Token，则返回错误响应
                return ResponseEntity.badRequest().body(new Response<>(400, "No Bearer Token found", null));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Response<>(400, e.getMessage(), null));
        }
    }

    // 教师查询所授课程的所有学生的成绩
    @GetMapping("/score")
    public ResponseEntity<Response<List<CourseScore>>> getTeacherScore(@RequestHeader("Authorization") String authHeader) {
        try {
            // 通常Bearer Token是以"Bearer "为前缀，这里移除前缀获取真正的token
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                // 假设getTeacherScore方法接受一个参数：令牌
                List<CourseScore> score = teacherService.getTeacherScore(token);
                return ResponseEntity.ok(new Response<>(200, "Get teacher score successful", score));
            } else {
                // 如果请求头不包含Bearer Token，则返回错误响应
                return ResponseEntity.badRequest().body(new Response<>(400, "No Bearer Token found", null));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Response<>(400, e.getMessage(), null));
        }
    }

    // 教师更新所上课程某学生的成绩
    @PutMapping("/score")
    public ResponseEntity<Response<String>> updateStudentScore(@RequestHeader("Authorization") String authHeader, @RequestBody TakeCourse takeCourse) {
        try {
            // 通常Bearer Token是以"Bearer "为前缀，这里移除前缀获取真正的token
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                // 假设updateStudentScore方法接受两个参数：令牌和学生成绩
                teacherService.updateStudentScore(token, takeCourse);
                return ResponseEntity.ok(new Response<>(200, "Update student score successful", null));
            } else {
                // 如果请求头不包含Bearer Token，则返回错误响应
                return ResponseEntity.badRequest().body(new Response<>(400, "No Bearer Token found", null));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Response<>(400, e.getMessage(), null));
        }
    }

    // 教师新增所上课程的学生
    @PostMapping("/addStudent")
    public ResponseEntity<Response<String>> addStudent(@RequestHeader("Authorization") String authHeader, @RequestBody TakeCourse takeCourse) {
        try {
            // 通常Bearer Token是以"Bearer "为前缀，这里移除前缀获取真正的token
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                // 假设addStudent方法接受两个参数：令牌和学生信息
                teacherService.addStudent(token, takeCourse);
                return ResponseEntity.ok(new Response<>(200, "Add student successful", null));
            } else {
                // 如果请求头不包含Bearer Token，则返回错误响应
                return ResponseEntity.badRequest().body(new Response<>(400, "No Bearer Token found", null));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Response<>(400, e.getMessage(), null));
        }
    }

    // 教师删除所上课程的学生
    @DeleteMapping("/deleteStudent")
    public ResponseEntity<Response<String>> deleteStudent(@RequestHeader("Authorization") String authHeader, @RequestBody TakeCourse takeCourse) {
        try {
            // 通常Bearer Token是以"Bearer "为前缀，这里移除前缀获取真正的token
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                // 假设deleteStudent方法接受两个参数：令牌和学生信息
                teacherService.deleteStudent(token, takeCourse);
                return ResponseEntity.ok(new Response<>(200, "Delete student successful", null));
            } else {
                // 如果请求头不包含Bearer Token，则返回错误响应
                return ResponseEntity.badRequest().body(new Response<>(400, "No Bearer Token found", null));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Response<>(400, e.getMessage(), null));
        }
    }

    // 教师修改密码
    @PutMapping("/password")
    public ResponseEntity<Response<String>> updatePassword(@RequestHeader("Authorization") String authHeader, @RequestBody Password password) {
        try {
            // 通常Bearer Token是以"Bearer "为前缀，这里移除前缀获取真正的token
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                // 假设updatePassword方法接受两个参数：令牌和新密码
                teacherService.updatePassword(token, password.getPassword());
                return ResponseEntity.ok(new Response<>(200, "Update password successful, please login again", null));
            } else {
                // 如果请求头不包含Bearer Token，则返回错误响应
                return ResponseEntity.badRequest().body(new Response<>(400, "No Bearer Token found", null));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Response<>(400, e.getMessage(), null));
        }
    }
}
