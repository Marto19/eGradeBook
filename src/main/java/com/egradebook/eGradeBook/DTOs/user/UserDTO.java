package com.egradebook.eGradeBook.DTOs.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO
{
    @NotBlank(message = "First name cannot be blank!")
    @Size(max = 30, message = "First name has to be up to 30 characters!")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only letters!")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank!")
    @Size(max = 30, message = "Last name has to be up to 30 characters!")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only letters!")
    private String lastName;

    @NotBlank(message = "Address cannot be blank!")
    @Size(max = 30, message = "Address has to be up to 30 characters!")
    @Pattern(regexp = "^[a-zA-Z0-9\\s,\\.]+$", message = "Address must contain only letters, numbers, spaces, commas, and periods!")
    private String address;

    @NotBlank(message = "Email cannot be blank!")
    @Pattern(regexp = "^[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-zA-Z]{1,8}$", message = "Invalid email address!")
    private String email;   //email associated with the user with the account

    private String passwordHash;

    @Size(max = 10, message = "Phone number has to be up to 10 digits!")
    @Pattern(regexp = "^[0-9]+$", message = "Phone number must contain only digits!")
    private String phoneNumber;
}
