package com.egradebook.eGradeBook.repositories;

import com.egradebook.eGradeBook.DTOs.AuthUserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import com.egradebook.eGradeBook.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsById(long id);

    Optional<User> findById(Long id);

    // Method to check if a user with a specific email exists
    boolean existsByEmail(String email);

    // Method to check if a user with a specific phone number exists
    boolean existsByPhoneNumber(String phoneNumber);

    // TODO -->
//    Optional<User> findUserByEmail(String email);


    // Returns AuthUserDTO without Roles<Set>
    @Query("SELECT new com.egradebook.eGradeBook.DTOs.AuthUserDTO(u.id, u.email, u.firstName, u.lastName, u.passwordHash, u.enabled) FROM User u WHERE u.email = :email")
    AuthUserDTO findByEmail(@Param("email") String email);

}
