package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.DTOs.role.CreateRoleDTO;
import com.egradebook.eGradeBook.DTOs.role.RoleDTO;
import com.egradebook.eGradeBook.exceptions.InvalidRoleException;
import com.egradebook.eGradeBook.services.RoleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/roles")
@AllArgsConstructor
public class RoleController
{
    private final RoleService roleService;

    @GetMapping
    public String showRolesPage(Model model)
    {
        model.addAttribute("roles", roleService.getAllRoles());
        return "role/roles";
    }

    @GetMapping("/create-role")
    public String showCreateRolePage(Model model)
    {
        model.addAttribute("role", new CreateRoleDTO());
        return "role/create-role";
    }

    @PostMapping("/create-role")
    public String createRole(@Valid @ModelAttribute("role") CreateRoleDTO createRoleDTO, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            return "role/create-role";
        }
        try
        {
            roleService.createRole(createRoleDTO);
            model.addAttribute("successMessage", "Role created successfully!");
        }
        catch (Exception e)
        {
            model.addAttribute("errorMessage", "Error creating role: " + e.getMessage());
        }
        return "redirect:/roles";
    }

    @GetMapping("/edit-role/{id}")
    public String showEditRolePage(@PathVariable Long id, Model model) throws InvalidRoleException
    {
        RoleDTO role = roleService.findRoleById(id)
                .orElseThrow(() -> new InvalidRoleException("Role not found"));

        model.addAttribute("role", role);
        return "role/edit-role";
    }

    @PostMapping("/edit-role")
    public String editRole(@Valid @ModelAttribute("role") RoleDTO roleDTO, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            return "role/edit-role";
        }
        try
        {
            roleService.updateRole(roleDTO);
            model.addAttribute("successMessage", "Role updated successfully!");
        }
        catch (Exception e)
        {
            model.addAttribute("errorMessage", "Error updating role: " + e.getMessage());
        }
        return "redirect:/roles";
    }


    @GetMapping("/delete-role/{id}")
    public String deleteRole(@PathVariable Long id, Model model)
    {
        try
        {
            roleService.deleteRole(id);
            model.addAttribute("successMessage", "Role deleted successfully!");
        }
        catch (Exception e)
        {
            model.addAttribute("errorMessage", "Error deleting role: " + e.getMessage());
        }
        return "redirect:/roles";
    }

}
