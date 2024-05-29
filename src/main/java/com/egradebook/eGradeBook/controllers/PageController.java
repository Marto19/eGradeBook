package com.egradebook.eGradeBook.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController
{
    @GetMapping("/")
    public String index() {
        return "home";
    }

//    @GetMapping("/")
//    public String index(@AuthenticationPrincipal Jwt jwt, Model model) {
//        model.addAttribute("jwt", jwt);
//        return "home";
//    }

//    String.format("Hello, %s!", jwt.getSubject());

    @GetMapping("/external")
    public String users()
    {
        return "external";
    }
}
