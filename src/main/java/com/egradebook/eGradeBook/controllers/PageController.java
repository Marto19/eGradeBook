package com.egradebook.eGradeBook.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

@Validated
@Controller
@AllArgsConstructor
public class PageController
{
    @GetMapping("/")
    public String home()
    {
        return "home";
    }


    @GetMapping("/external")
    public String users()
    {
        return "external";
    }
}
