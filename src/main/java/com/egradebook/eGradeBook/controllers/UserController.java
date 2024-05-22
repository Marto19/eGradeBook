package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.repositories.UserRepository;
import com.egradebook.eGradeBook.services.KeyCloakService;
import com.egradebook.eGradeBook.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    private final KeyCloakService keyCloakService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model)
    {
        try
        {
            String token = keyCloakService.authenticate(username, password);
            model.addAttribute("token", token);
            return "home";
        } catch (Exception e)
        {
            model.addAttribute("loginError", true);
            return "login";
        }
    }
}
