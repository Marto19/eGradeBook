package com.egradebook.eGradeBook.services.serviceImplementation;

import static org.junit.jupiter.api.Assertions.*;

import com.egradebook.eGradeBook.DTOs.role.CreateRoleDTO;
import com.egradebook.eGradeBook.DTOs.role.RoleDTO;
import com.egradebook.eGradeBook.entities.Role;
import com.egradebook.eGradeBook.exceptions.InvalidRoleException;
import com.egradebook.eGradeBook.repositories.RoleRepository;
import com.egradebook.eGradeBook.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest
{
    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    private Role role;
    private RoleDTO roleDTO;
    private CreateRoleDTO createRoleDTO;

    @BeforeEach
    void setUp()
    {
        role = new Role(1L, "ROLE_ADMIN", null);
        roleDTO = new RoleDTO(1L, "ROLE_ADMIN");
        createRoleDTO = new CreateRoleDTO("ROLE_ADMIN");
    }

    @Test
    void testCreateRole_Success()
    {
        when(roleRepository.findByName(createRoleDTO.getName())).thenReturn(Optional.empty());

        roleService.createRole(createRoleDTO);

        verify(roleRepository, times(1)).save(any(Role.class));
    }

    @Test
    void testCreateRole_RoleAlreadyExists()
    {
        // Arrange
        when(roleRepository.findByName(createRoleDTO.getName())).thenReturn(Optional.of(role));

        // Act & Assert
        assertThrows(InvalidRoleException.class, () -> roleService.createRole(createRoleDTO));
        verify(roleRepository, never()).save(any(Role.class));
    }

    @Test
    void testUpdateRole_Success()
    {
        // Arrange
        when(roleRepository.findById(roleDTO.getId())).thenReturn(Optional.of(role));

        // Act
        roleService.updateRole(roleDTO);

        // Assert
        verify(roleRepository, times(1)).save(role);
        assertEquals(roleDTO.getName(), role.getName());
    }

    @Test
    void testUpdateRole_RoleNotFound()
    {
        // Arrange
        when(roleRepository.findById(roleDTO.getId())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(InvalidRoleException.class, () -> roleService.updateRole(roleDTO));
        verify(roleRepository, never()).save(any(Role.class));
    }

    @Test
    void testDeleteRole_Success()
    {
        // Arrange
        when(roleRepository.findById(role.getId())).thenReturn(Optional.of(role));
        when(userRepository.findByRoles_Id(role.getId())).thenReturn(List.of());

        // Act
        roleService.deleteRole(role.getId());

        // Assert
        verify(userRepository, times(1)).findByRoles_Id(role.getId());
        verify(roleRepository, times(1)).delete(role);
    }

    @Test
    void testDeleteRole_RoleNotFound()
    {
        // Arrange
        when(roleRepository.findById(role.getId())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(InvalidRoleException.class, () -> roleService.deleteRole(role.getId()));
        verify(userRepository, never()).findByRoles_Id(anyLong());
        verify(roleRepository, never()).delete(any(Role.class));
    }

    @Test
    void testGetAllRoles()
    {
        // Arrange
        when(roleRepository.findAll()).thenReturn(List.of(role));

        // Act
        List<Role> roles = roleService.getAllRoles();

        // Assert
        assertEquals(1, roles.size());
        verify(roleRepository, times(1)).findAll();
    }

    @Test
    void testFindRoleByName_Success()
    {
        when(roleRepository.findByName(role.getName())).thenReturn(Optional.of(role));

        Optional<RoleDTO> foundRole = roleService.findRoleByName(role.getName());

        assertTrue(foundRole.isPresent());

        verify(roleRepository, times(1)).findByName(role.getName());
    }

    @Test
    void testFindRoleByName_RoleNotFound()
    {
        // Arrange
        when(roleRepository.findByName(role.getName())).thenReturn(Optional.empty());

        // Act
        Optional<RoleDTO> foundRole = roleService.findRoleByName(role.getName());

        // Assert
        assertFalse(foundRole.isPresent());
        verify(roleRepository, times(1)).findByName(role.getName());
    }

    @Test
    void testFindRoleById_Success()
    {
        when(roleRepository.findById(role.getId())).thenReturn(Optional.of(role));

        Optional<RoleDTO> foundRole = roleService.findRoleById(role.getId());

        assertTrue(foundRole.isPresent());

        verify(roleRepository, times(1)).findById(role.getId());
    }

    @Test
    void testFindRoleById_RoleNotFound()
    {
        // Arrange
        when(roleRepository.findById(role.getId())).thenReturn(Optional.empty());

        // Act
        Optional<RoleDTO> foundRole = roleService.findRoleById(role.getId());

        // Assert
        assertFalse(foundRole.isPresent());
        verify(roleRepository, times(1)).findById(role.getId());
    }
}