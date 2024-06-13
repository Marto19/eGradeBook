package com.egradebook.eGradeBook.services;

import com.egradebook.eGradeBook.DTOs.role.CreateRoleDTO;
import com.egradebook.eGradeBook.DTOs.role.RoleDTO;
import com.egradebook.eGradeBook.entities.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RoleService
{
    void createRole(CreateRoleDTO createRoleDTO);

    void updateRole(RoleDTO roleDTO);

    void deleteRole(Long id);

    List<Role> getAllRoles();

    Optional<RoleDTO> findRoleByName(String name);

    Optional<RoleDTO> findRoleById(Long id);
}
