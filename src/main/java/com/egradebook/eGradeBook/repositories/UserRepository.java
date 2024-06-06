package com.egradebook.eGradeBook.repositories;

import com.egradebook.eGradeBook.DTOs.user.AuthUserDTO;
import com.egradebook.eGradeBook.DTOs.user.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import com.egradebook.eGradeBook.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsById(long id);

    Optional<User> findById(Long id);

    List<User> findAll();

    Optional<User> findUserByEmail(String email);

    // Method to check if a user with a specific email exists
    boolean existsByEmail(String email);

    // Method to check if a user with a specific phone number exists
    boolean existsByPhoneNumber(String phoneNumber);

    // Returns AuthUserDTO without Roles<Set>
    @Query("SELECT new com.egradebook.eGradeBook.DTOs.user.AuthUserDTO(u.id, u.email, u.firstName, u.lastName, u.passwordHash, u.enabled) FROM User u WHERE u.email = :email")
    Optional<AuthUserDTO> findAuthUserByEmail(@Param("email") String email);

    @Query("SELECT new com.egradebook.eGradeBook.DTOs.user.UserDTO(u.id, u.firstName, u.lastName, u.email, u.address, u.phoneNumber) FROM User u")
    List<UserDTO> findAllUserDTO();
}
