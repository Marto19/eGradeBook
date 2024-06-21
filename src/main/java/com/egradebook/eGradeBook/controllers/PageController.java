package com.egradebook.eGradeBook.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class PageController
{
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
