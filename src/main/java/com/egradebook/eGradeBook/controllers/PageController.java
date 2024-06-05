package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {

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
