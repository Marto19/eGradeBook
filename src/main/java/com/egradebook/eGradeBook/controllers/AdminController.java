package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.DTOs.user.CreateUserDTO;
import com.egradebook.eGradeBook.DTOs.user.UpdateUserDTO;
import com.egradebook.eGradeBook.exceptions.UserNotFoundException;
import com.egradebook.eGradeBook.services.RoleService;
import com.egradebook.eGradeBook.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController
{
    private final UserService userService;

    private final RoleService roleService;

    @GetMapping
    public String showAdminPage(Model model)
    {
        model.addAttribute("users", userService.getAllUsers());

        return "/admin";
    }

    @GetMapping("/create-user")
    public String showCreateUserPage(Model model)
    {
        model.addAttribute("user", new CreateUserDTO());

        model.addAttribute("roles", roleService.getAllRoles());

        return "sisu/create-user";
    }

    @PostMapping("/create-user")
    public String createUser(@Valid @ModelAttribute("user") CreateUserDTO createUserDto, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("roles", roleService.getAllRoles());
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
            model.addAttribute("roles", roleService.getAllRoles());
        }
        return "redirect:/admin";
    }

    @GetMapping("/edit-user/{email}")
    public String showEditUserPage(@PathVariable String email, Model model) throws UserNotFoundException
    {
        UpdateUserDTO user = userService.findUpdateUserDTOByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));

        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());

        return "sisu/edit-user";
    }

    @PostMapping("/edit-user")
    public String editUser(@Valid @ModelAttribute("user") UpdateUserDTO updateUserDto, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("roles", roleService.getAllRoles());
            return "sisu/edit-user";
        }
        try
        {
            userService.updateUser(updateUserDto);
            model.addAttribute("successMessage", "User updated successfully!");
        }
        catch (Exception e)
        {
            model.addAttribute("errorMessage", "Error updating user: " + e.getMessage());
        }
        return "redirect:/admin";
    }

    @GetMapping("/delete-user/{email}")
    public String deleteUser(@PathVariable String email, Model model)
    {
        try
        {
            userService.deleteUser(email);
            model.addAttribute("successMessage", "User deleted successfully!");
        }
        catch (Exception e)
        {
            model.addAttribute("errorMessage", "Error deleting user: " + e.getMessage());
        }
        return "redirect:/admin";
    }

    @GetMapping("/makeActive-user/{email}")
    public String makeUserActive(@PathVariable String email, Model model)
    {
        try
        {
            userService.makeUserActive(email);
            model.addAttribute("successMessage", "User activated successfully!");
        }
        catch (Exception e)
        {
            model.addAttribute("errorMessage", "Error activating user: " + e.getMessage());
        }
        return "redirect:/admin";
    }
}
