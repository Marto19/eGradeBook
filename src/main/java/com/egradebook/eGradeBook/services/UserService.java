package com.egradebook.eGradeBook.services;

import com.egradebook.eGradeBook.DTOs.AuthUserDTO;
import com.egradebook.eGradeBook.DTOs.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService
{
    AuthUserDTO findByEmail(String email);

    void createUser(UserDTO userDto);
}
