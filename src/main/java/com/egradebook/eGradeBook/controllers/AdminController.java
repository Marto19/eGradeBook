package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.DTOs.user.UserDTO;
import com.egradebook.eGradeBook.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController
{
    private final UserService userService;


    @GetMapping("/create-user")
    public String showCreateUserPage(Model model) {
        model.addAttribute("user", new UserDTO());
        return "sisu/create-user";
    }

    @PostMapping("/create-user")
    public String createUser(@ModelAttribute("user") UserDTO userDto, Model model) {
        try
        {
            userService.createUser(userDto);

            model.addAttribute("successMessage", "User created successfully!");
        }
        catch (Exception e)
        {
            model.addAttribute("errorMessage", "Error creating user: " + e.getMessage());
        }
        return "/admin";
    }
}
