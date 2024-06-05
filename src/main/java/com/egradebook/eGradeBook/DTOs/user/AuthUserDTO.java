package com.egradebook.eGradeBook.DTOs.user;

import com.egradebook.eGradeBook.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@ToString
@Getter @Setter
@AllArgsConstructor
public class AuthUserDTO
{

    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    private String passwordHash;

    private Boolean enabled;

    private Set<Role> authorities;

    public AuthUserDTO(Long id, String email, String firstName, String lastName, String passwordHash, Boolean enabled) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passwordHash = passwordHash;
        this.enabled = enabled;
    }
}