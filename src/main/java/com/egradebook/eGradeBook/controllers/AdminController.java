package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.DTOs.user.CreateUserDTO;
import com.egradebook.eGradeBook.DTOs.user.UpdateUserDTO;
import com.egradebook.eGradeBook.entities.User;
import com.egradebook.eGradeBook.exceptions.UserNotFoundException;
import com.egradebook.eGradeBook.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController
{
    private final UserService userService;

    @GetMapping("/create-user")
    public String showCreateUserPage(Model model)
    {
        model.addAttribute("user", new CreateUserDTO());

        return "sisu/create-user";
    }

    @PostMapping("/create-user")
    public String createUser(@Valid @ModelAttribute("user") CreateUserDTO createUserDto, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            return "sisu/create-user";
        }
        try
        {
            userService.createUser(createUserDto);

            model.addAttribute("successMessage", "User created successfully!");
        }
        catch (Exception e)
        {
            model.addAttribute("errorMessage", "Error creating user: " + e.getMessage());
        }
        return "/admin";
    }


    @GetMapping("/edit-user/{id}")
    public String showEditUserPage(@PathVariable("id") long id, Model model) {
        User user = null;
        try {
            user = userService.findById(id);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (user == null) {
            return "error";
        }
        model.addAttribute("user", user);
        return "admin/edit-user";
    }

    @PostMapping("/edit-user")
    public String editUser(@Valid @ModelAttribute("user") UpdateUserDTO updateUserDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/edit-user";
        }
        try {
            userService.updateUser(updateUserDto);
            model.addAttribute("successMessage", "User updated successfully!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error updating user: " + e.getMessage());
        }
        return "redirect:/admin";
    }


    @GetMapping("/delete-user/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting user: " + e.getMessage());
        }
        return "redirect:/admin";
    }


/*
    @GetMapping("/edit-user/{email}")
    public String showEditUserPage(@PathVariable String email, Model model)
    {
        UpdateUserDTO user = userService.getUserByEmail(email);
        model.addAttribute("user", user);
        return "admin/edit-user";
    }



    @PostMapping("/edit-user/{email}")
    public String editUser(@PathVariable String email, @ModelAttribute("user") UpdateUserDTO userDto, Model model) {
        try {
            userService.updateUser(email, userDto);
            model.addAttribute("successMessage", "User updated successfully!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error updating user: " + e.getMessage());
        }
        return "redirect:/admin/list-users";
    }

    @GetMapping("/delete-user/{email}")
    public String deleteUser(@PathVariable String email, Model model) {
        try {
            userService.deleteUser(email);
            model.addAttribute("successMessage", "User deleted successfully!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error deleting user: " + e.getMessage());
        }
        return "redirect:/admin/list-users";
    }

 */
}
