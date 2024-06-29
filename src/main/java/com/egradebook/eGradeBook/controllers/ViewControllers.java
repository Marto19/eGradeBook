package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
@AllArgsConstructor
public class ViewControllers {
    private final UserService userService;

    //admin
    @GetMapping("/admin")
    public String showAdminPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";         //todo: change with admin view
    }

    //teacher
    @GetMapping("/teacher")
    public String showTeacherPage(Model model) {
        return "/teacher/teacher-view";
    }

    //student
    @GetMapping("/student")
    public String showStudentPage(Model model) {
        return "student";       //todo: change with student view
    }

    //parent
    @GetMapping("/parent")
    public String showParentPage(Model model) {
        return "/parent/parent-view";        //todo: change with parent view
    }

    //principal
    @GetMapping("/principal")
    public String showPrincipalPage(Model model) {
        return "/principal/principal-view";         //todo: change with principal view
    }
}
