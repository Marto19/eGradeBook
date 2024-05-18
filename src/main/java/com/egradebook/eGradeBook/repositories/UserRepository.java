package com.egradebook.eGradeBook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.egradebook.eGradeBook.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsById(long id);

    Optional<User> findById(Long id);

    // Method to check if a user with a specific email exists
    boolean existsByEmail(String email);

    // Method to check if a user with a specific phone number exists
    boolean existsByPhoneNumber(String phoneNumber);
}
