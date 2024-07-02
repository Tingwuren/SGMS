package cn.edu.bupt.sgms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class StartController {
    @RequestMapping("")
    public String start() {
        return "login";
    }

    @RequestMapping("/student/workspace")
    public String workspace() {
        return "student/workspace";
    }

    @RequestMapping("/teacher/workspace")
    public String teacherWorkspace() {
        return "teacher/workspace";
    }

    @RequestMapping("/admin/workspace")
    public String adminWorkspace() {
        return "admin/workspace";
    }
}
