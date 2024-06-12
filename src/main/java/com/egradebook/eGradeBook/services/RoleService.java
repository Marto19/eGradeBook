package com.egradebook.eGradeBook.services;

import com.egradebook.eGradeBook.DTOs.RoleDTO;
import com.egradebook.eGradeBook.entities.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService
{
    void createRole(RoleDTO roleDTO);

    void updateRole(RoleDTO roleDTO);

    void deleteRole(RoleDTO roleDTO);

    List<Role> getAllRoles();
}
