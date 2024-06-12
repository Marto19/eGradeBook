package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.DTOs.user.UpdateUserDTO;
import com.egradebook.eGradeBook.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class PageController
{

    private UserService userService;
    @GetMapping("/")
    public String showHomePage()
    {
        return "home";
    }

    @GetMapping("/login")
    public String showLoginPage()
    {
        return "/sisu/login";
    }
}
