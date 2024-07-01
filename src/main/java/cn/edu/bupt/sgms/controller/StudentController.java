package cn.edu.bupt.sgms.controller;

import cn.edu.bupt.sgms.DTO.CourseScore;
import cn.edu.bupt.sgms.DTO.LoginRequest;
import cn.edu.bupt.sgms.DTO.Response;
import cn.edu.bupt.sgms.entity.Student;
import cn.edu.bupt.sgms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // 学生登录
    @PostMapping("/login")
    public ResponseEntity<Response<String>> loginStudent(@RequestBody LoginRequest loginRequest) {
        try {
            String token = studentService.login(loginRequest.getUsername(), loginRequest.getPassword());
            return ResponseEntity.ok(new Response<>(200, "Login successful", token));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Response<>(400, e.getMessage(), null));
        }
    }

    // 学生注销
    @PostMapping("/logout")
    public ResponseEntity<Response<String>> logoutStudent(@RequestHeader("Authorization") String authHeader) {
        try {
            // 通常Bearer Token是以"Bearer "为前缀，这里移除前缀获取真正的token
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                studentService.logout(token);
                return ResponseEntity.ok(new Response<>(200, "Logout successful", null));
            } else {
                // 如果请求头不包含Bearer Token，则返回错误响应
                return ResponseEntity.badRequest().body(new Response<>(400, "No Bearer Token found", null));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Response<>(400, e.getMessage(), null));
        }
    }

    // 学生获取自己的基本信息
    @GetMapping("/info")
    public ResponseEntity<Response<Student>> getStudentInfo(@RequestHeader("Authorization") String authHeader) {
        try {
            // 通常Bearer Token是以"Bearer "为前缀，这里移除前缀获取真正的token
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                Student student = studentService.getStudentInfo(token);
                return ResponseEntity.ok(new Response<>(200, "Get student info successful", student));
            } else {
                // 如果请求头不包含Bearer Token，则返回错误响应
                return ResponseEntity.badRequest().body(new Response<>(400, "No Bearer Token found", null));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Response<>(400, e.getMessage(), null));
        }
    }

    // 学生查询自己的成绩
    @GetMapping("/score")
    public ResponseEntity<Response<List<CourseScore>>> getStudentScore(@RequestHeader("Authorization") String authHeader) {
        try {
            // 通常Bearer Token是以"Bearer "为前缀，这里移除前缀获取真正的token
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                // 假设getStudentScore方法接受一个参数：令牌
                List<CourseScore> score = studentService.getStudentScore(token);
                return ResponseEntity.ok(new Response<>(200, "Get student score successful", score));
            } else {
                // 如果请求头不包含Bearer Token，则返回错误响应
                return ResponseEntity.badRequest().body(new Response<>(400, "No Bearer Token found", null));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Response<>(400, e.getMessage(), null));
        }
    }
}
