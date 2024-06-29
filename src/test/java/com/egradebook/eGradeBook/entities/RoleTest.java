package com.egradebook.eGradeBook.entities;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class RoleTest
{
    @Test
    void testRoleConstructorAndGetters()
    {
        Long roleId = 1L;
        String roleName = "ROLE_ADMIN";

        Role role = new Role(roleId, roleName, null);

        assertEquals(roleId, role.getId());
        assertEquals(roleName, role.getName());
        assertNull(role.getUsers());
    }

    @Test
    void testRoleSetters()
    {
        Role role = new Role();
        Long roleId = 1L;
        String roleName = "ROLE_ADMIN";
        Set<User> users = Set.of(new User());

        role.setId(roleId);
        role.setName(roleName);
        role.setUsers(users);

        assertEquals(roleId, role.getId());
        assertEquals(roleName, role.getName());
        assertEquals(users, role.getUsers());
    }

    @Test
    void testRoleBuilder()
    {
        Long roleId = 1L;
        String roleName = "ROLE_ADMIN";
        Set<User> users = Set.of(new User());

        Role role = Role.builder()
                .id(roleId)
                .name(roleName)
                .users(users)
                .build();

        assertEquals(roleId, role.getId());
        assertEquals(roleName, role.getName());
        assertEquals(users, role.getUsers());
    }

    @Test
    void testRoleToString()
    {
        Role role = Role.builder()
                .id(1L)
                .name("ROLE_ADMIN")
                .build();

        String roleString = role.toString();

        assertNotNull(roleString);
        assertTrue(roleString.contains("ROLE_ADMIN"));
    }

    @Test
    void testAddUserToRole()
    {
        Role role = new Role("ROLE_ADMIN");
        User user = new User();
        Set<User> users = new HashSet<>();
        role.setUsers(users);

        role.getUsers().add(user);

        assertTrue(role.getUsers().contains(user));
    }
}