package com.egradebook.eGradeBook.services;

import com.egradebook.eGradeBook.DTOs.user.AuthUserDTO;
import com.egradebook.eGradeBook.DTOs.user.CreateUserDTO;
import com.egradebook.eGradeBook.DTOs.user.UpdateUserDTO;
import com.egradebook.eGradeBook.DTOs.user.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService
{
    AuthUserDTO findByEmail(String email);

    void createUser(CreateUserDTO createUserDto);

    void updateUser(UpdateUserDTO createUserDTO);

    void deleteUser(String email);

    List<UpdateUserDTO> getAllUsers();

    List<UserDTO> getAllUsersDto();
}
