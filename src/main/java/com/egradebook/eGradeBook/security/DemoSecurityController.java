package com.egradebook.eGradeBook.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoSecurityController {
    @GetMapping
    public String hello() {
        return "Hello, security!";
    }

    @GetMapping("/hello2")
    public String hello2() {
        return "Hello, security - ADMIN!";
    }


}
