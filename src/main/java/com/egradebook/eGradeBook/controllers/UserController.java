package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.repositories.UserRepository;
import com.egradebook.eGradeBook.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * This class is used to create the controller for the User entity.
 * It contains methods that will be used to interact with the User entity.
 * The methods include creating, updating, deleting, and getting users.
 *
 */
@Validated
@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UserController
{
    private final UserService userService;

    private final UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginProcess() {
        return "login";
    }


}
