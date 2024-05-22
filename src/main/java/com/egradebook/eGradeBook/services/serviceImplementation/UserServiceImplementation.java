package com.egradebook.eGradeBook.services.serviceImplementation;

import com.egradebook.eGradeBook.entities.User;
import com.egradebook.eGradeBook.exceptions.EntityAlreadyExistsException;
import com.egradebook.eGradeBook.repositories.UserRepository;
import com.egradebook.eGradeBook.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * This class is used to implement the methods that will be used to interact with the User entity.
 * The methods include creating, updating, and deleting users.
 */
@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService {

    @Override
    public String loginUser(String username, String password) {
        return "";
    }

}
