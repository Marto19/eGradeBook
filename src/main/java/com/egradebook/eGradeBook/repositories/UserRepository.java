package com.egradebook.eGradeBook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.egradebook.eGradeBook.entities.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findUsersById(long id);

    Optional<User> findByFirstName(String username);

    Optional<User> findByLastName(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByPhoneNumber(String phoneNumber);


}
