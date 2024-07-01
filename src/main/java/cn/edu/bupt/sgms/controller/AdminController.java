package cn.edu.bupt.sgms.controller;

import cn.edu.bupt.sgms.DTO.*;
import cn.edu.bupt.sgms.entity.Student;
import cn.edu.bupt.sgms.entity.Teacher;
import cn.edu.bupt.sgms.service.AdminService;
import cn.edu.bupt.sgms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // 管理员登录
    @PostMapping("/login")
    public ResponseEntity<Response<String>> loginAdmin(@RequestBody LoginRequest loginRequest) {
        try {
            String token = adminService.login(loginRequest.getUsername(), loginRequest.getPassword());
            return ResponseEntity.ok(new Response<>(200, "Login successful", token));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Response<>(400, e.getMessage(), null));
        }
    }

    // 管理员注销
    @PostMapping("/logout")
    public ResponseEntity<Response<String>> logoutAdmin(@RequestHeader("Authorization") String authHeader) {
        try {
            // 通常Bearer Token是以"Bearer "为前缀，这里移除前缀获取真正的token
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                adminService.logout(token);
                return ResponseEntity.ok(new Response<>(200, "Logout successful", null));
            } else {
                // 如果请求头不包含Bearer Token，则返回错误响应
                return ResponseEntity.badRequest().body(new Response<>(400, "No Bearer Token found", null));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Response<>(400, e.getMessage(), null));
        }
    }

    // 管理员查询对应ID学生的基本信息
    @GetMapping("/studentInfo/{studentId}")
    public ResponseEntity<Response<StudentInfo>> getStudentInfo(@RequestHeader("Authorization") String authHeader, @PathVariable("studentId") Integer studentId) {
        try {
            // 通常Bearer Token是以"Bearer "为前缀，这里移除前缀获取真正的token
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                StudentInfo studentInfo = adminService.getStudentInfo(token, studentId);
                return ResponseEntity.ok(new Response<>(200, "Get student info successful", studentInfo));
            } else {
                // 如果请求头不包含Bearer Token，则返回错误响应
                return ResponseEntity.badRequest().body(new Response<>(400, "No Bearer Token found", null));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Response<>(400, e.getMessage(), null));
        }
    }

    // 管理员查询所有学生的基本信息
    @GetMapping("/studentInfo")
    public ResponseEntity<Response<List<StudentInfo>>> getAllStudentInfo(@RequestHeader("Authorization") String authHeader) {
        try {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                List<StudentInfo> studentInfoList = adminService.getAllStudentInfo(token);
                return ResponseEntity.ok(new Response<>(200, "Get all student info successful", studentInfoList));
            } else {
                return ResponseEntity.badRequest().body(new Response<>(400, "No Bearer Token found", null));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Response<>(400, e.getMessage(), null));
        }
    }

    // 管理员查询教师的基本信息
    @GetMapping("/teacherInfo/{teacherId}")
    public ResponseEntity<Response<TeacherInfo>> getTeacherInfo(@RequestHeader("Authorization") String authHeader, @PathVariable("teacherId") Integer teacherId) {
        try {
            // 通常Bearer Token是以"Bearer "为前缀，这里移除前缀获取真正的token
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                TeacherInfo teacherInfo = adminService.getTeacherInfo(token, teacherId);
                return ResponseEntity.ok(new Response<>(200, "Get teacher info successful", teacherInfo));
            } else {
                // 如果请求头不包含Bearer Token，则返回错误响应
                return ResponseEntity.badRequest().body(new Response<>(400, "No Bearer Token found", null));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Response<>(400, e.getMessage(), null));
        }
    }

    // 管理员查询所有教师的基本信息
    @GetMapping("/teacherInfo")
    public ResponseEntity<Response<List<TeacherInfo>>> getAllTeacherInfo(@RequestHeader("Authorization") String authHeader) {
        try {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                List<TeacherInfo> teacherInfoList = adminService.getAllTeacherInfo(token);
                return ResponseEntity.ok(new Response<>(200, "Get all teacher info successful", teacherInfoList));
            } else {
                return ResponseEntity.badRequest().body(new Response<>(400, "No Bearer Token found", null));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Response<>(400, e.getMessage(), null));
        }
    }

    // 管理员修改学生的基本信息
    @PutMapping("/studentInfo/{studentId}")
    public ResponseEntity<Response<String>> updateStudentInfo(@RequestHeader("Authorization") String authHeader, @PathVariable("studentId") Integer studentId, @RequestBody StudentInfo studentInfo) {
        try {
            // 通常Bearer Token是以"Bearer "为前缀，这里移除前缀获取真正的token
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                adminService.updateStudentInfo(token, studentId, studentInfo);
                return ResponseEntity.ok(new Response<>(200, "Update student info successful", null));
            } else {
                // 如果请求头不包含Bearer Token，则返回错误响应
                return ResponseEntity.badRequest().body(new Response<>(400, "No Bearer Token found", null));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Response<>(400, e.getMessage(), null));
        }
    }

    // 管理员修改教师的基本信息
    @PutMapping("/teacherInfo/{teacherId}")
    public ResponseEntity<Response<String>> updateTeacherInfo(@RequestHeader("Authorization") String authHeader, @PathVariable("teacherId") Integer teacherId, @RequestBody TeacherInfo teacherInfo) {
        try {
            // 通常Bearer Token是以"Bearer "为前缀，这里移除前缀获取真正的token
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                adminService.updateTeacherInfo(token, teacherId, teacherInfo);
                return ResponseEntity.ok(new Response<>(200, "Update teacher info successful", null));
            } else {
                // 如果请求头不包含Bearer Token，则返回错误响应
                return ResponseEntity.badRequest().body(new Response<>(400, "No Bearer Token found", null));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Response<>(400, e.getMessage(), null));
        }
    }

    // 管理员删除学生
    @DeleteMapping("/studentInfo/{studentId}")
    public ResponseEntity<Response<String>> deleteStudent(@RequestHeader("Authorization") String authHeader, @PathVariable("studentId") Integer studentId) {
        try {
            // 通常Bearer Token是以"Bearer "为前缀，这里移除前缀获取真正的token
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                adminService.deleteStudent(token, studentId);
                return ResponseEntity.ok(new Response<>(200, "Delete student successful", null));
            } else {
                // 如果请求头不包含Bearer Token，则返回错误响应
                return ResponseEntity.badRequest().body(new Response<>(400, "No Bearer Token found", null));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Response<>(400, e.getMessage(), null));
        }
    }

    // 管理员删除教师
    @DeleteMapping("/teacherInfo/{teacherId}")
    public ResponseEntity<Response<String>> deleteTeacher(@RequestHeader("Authorization") String authHeader, @PathVariable("teacherId") Integer teacherId) {
        try {
            // 通常Bearer Token是以"Bearer "为前缀，这里移除前缀获取真正的token
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                adminService.deleteTeacher(token, teacherId);
                return ResponseEntity.ok(new Response<>(200, "Delete teacher successful", null));
            } else {
                // 如果请求头不包含Bearer Token，则返回错误响应
                return ResponseEntity.badRequest().body(new Response<>(400, "No Bearer Token found", null));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Response<>(400, e.getMessage(), null));
        }
    }

    // 管理员查询课程的基本信息
    @GetMapping("/courseInfo/{courseId}")
    public ResponseEntity<Response<CourseInfo>> getCourseInfo(@RequestHeader("Authorization") String authHeader, @PathVariable("courseId") Integer courseId) {
        try {
            // 通常Bearer Token是以"Bearer "为前缀，这里移除前缀获取真正的token
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                CourseInfo courseInfo = adminService.getCourseInfo(token, courseId);
                return ResponseEntity.ok(new Response<>(200, "Get course info successful", courseInfo));
            } else {
                // 如果请求头不包含Bearer Token，则返回错误响应
                return ResponseEntity.badRequest().body(new Response<>(400, "No Bearer Token found", null));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Response<>(400, e.getMessage(), null));
        }
    }

    // 管理员查询所有课程的基本信息
    @GetMapping("/courseInfo")
    public ResponseEntity<Response<List<CourseInfo>>> getAllCourseInfo(@RequestHeader("Authorization") String authHeader) {
        try {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                List<CourseInfo> courseInfoList = adminService.getAllCourseInfo(token);
                return ResponseEntity.ok(new Response<>(200, "Get all course info successful", courseInfoList));
            } else {
                return ResponseEntity.badRequest().body(new Response<>(400, "No Bearer Token found", null));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Response<>(400, e.getMessage(), null));
        }
    }

    // 管理员修改课程的基本信息
    @PutMapping("/courseInfo/{courseId}")
    public ResponseEntity<Response<String>> updateCourseInfo(@RequestHeader("Authorization") String authHeader, @PathVariable("courseId") Integer courseId, @RequestBody CourseInfo courseInfo) {
        try {
            // 通常Bearer Token是以"Bearer "为前缀，这里移除前缀获取真正的token
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                adminService.updateCourseInfo(token, courseId, courseInfo);
                return ResponseEntity.ok(new Response<>(200, "Update course info successful", null));
            } else {
                // 如果请求头不包含Bearer Token，则返回错误响应
                return ResponseEntity.badRequest().body(new Response<>(400, "No Bearer Token found", null));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Response<>(400, e.getMessage(), null));
        }
    }

    // 管理员删除课程
    @DeleteMapping("/courseInfo/{courseId}")
    public ResponseEntity<Response<String>> deleteCourse(@RequestHeader("Authorization") String authHeader, @PathVariable("courseId") Integer courseId) {
        try {
            // 通常Bearer Token是以"Bearer "为前缀，这里移除前缀获取真正的token
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                adminService.deleteCourse(token, courseId);
                return ResponseEntity.ok(new Response<>(200, "Delete course successful", null));
            } else {
                // 如果请求头不包含Bearer Token，则返回错误响应
                return ResponseEntity.badRequest().body(new Response<>(400, "No Bearer Token found", null));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Response<>(400, e.getMessage(), null));
        }
    }
}
