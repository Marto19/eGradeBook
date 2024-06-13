package com.egradebook.eGradeBook.DTOs.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateRoleDTO
{
    @NotBlank(message = "Role name cannot be blank!")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Role must contain only letters!")
    private String name;
}
