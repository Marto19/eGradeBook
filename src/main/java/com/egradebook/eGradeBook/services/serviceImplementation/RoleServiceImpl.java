package com.egradebook.eGradeBook.services.serviceImplementation;

import com.egradebook.eGradeBook.DTOs.role.CreateRoleDTO;
import com.egradebook.eGradeBook.DTOs.role.RoleDTO;
import com.egradebook.eGradeBook.entities.Role;
import com.egradebook.eGradeBook.entities.User;
import com.egradebook.eGradeBook.exceptions.InvalidRoleException;
import com.egradebook.eGradeBook.repositories.RoleRepository;
import com.egradebook.eGradeBook.repositories.UserRepository;
import com.egradebook.eGradeBook.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService
{
    private final RoleRepository roleRepository;

    private final UserRepository userRepository;


    @Override
    public void createRole(CreateRoleDTO createRoleDTO)
    {
        if (roleRepository.findByName(createRoleDTO.getName()).isPresent())
        {

            Role newRole = Role.builder()
                            .name(createRoleDTO.getName())
                                    .build();

            roleRepository.save(newRole);
        }
        else
        {
            throw new InvalidRoleException("Role already exists");
        }

    }

    @Override
    public void updateRole(RoleDTO roleDTO)
    {
        Role existingRole = roleRepository.findById(roleDTO.getId())
                .orElseThrow(() -> new InvalidRoleException("Role does not exist."));

        existingRole.setName(roleDTO.getName());

        roleRepository.save(existingRole);
    }

    @Override
    public void deleteRole(Long id)
    {
        Role existingRole = roleRepository.findById(id)

                .orElseThrow(() -> new InvalidRoleException("Role does not exist."));

        // Remove the role from all users
        List<User> usersWithRole = userRepository.findByRoles_Id(id);

        for (User user : usersWithRole) {
            user.getRoles().remove(existingRole);
            userRepository.save(user);
        }

        // Now delete the role
        roleRepository.delete(existingRole);
    }

    @Override
    public List<Role> getAllRoles()
    {
        return roleRepository.findAll();
    }

    @Override
    public Optional<RoleDTO> findRoleByName(String name)
    {
        return roleRepository.findByName(name)
                .map(role -> new RoleDTO(role.getId(), role.getName()));
    }

    @Override
    public Optional<RoleDTO> findRoleById(Long id)
    {
        return roleRepository.findById(id)
                .map(role -> new RoleDTO(role.getId(), role.getName()));
    }


}
