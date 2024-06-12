package com.egradebook.eGradeBook.services.serviceImplementation;

import com.egradebook.eGradeBook.DTOs.RoleDTO;
import com.egradebook.eGradeBook.entities.Role;
import com.egradebook.eGradeBook.repositories.RoleRepository;
import com.egradebook.eGradeBook.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService
{
    private final RoleRepository roleRepository;


    @Override
    public void createRole(RoleDTO roleDTO) {

    }

    @Override
    public void updateRole(RoleDTO roleDTO) {

    }

    @Override
    public void deleteRole(RoleDTO roleDTO) {

    }

    @Override
    public List<Role> getAllRoles()
    {
        return roleRepository.findAll();
    }
}
