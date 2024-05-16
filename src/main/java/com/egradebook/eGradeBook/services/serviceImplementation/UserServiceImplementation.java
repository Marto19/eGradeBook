package com.egradebook.eGradeBook.services.serviceImplementation;

import com.egradebook.eGradeBook.entities.User;
import com.egradebook.eGradeBook.exceptions.EntityAlreadyExistsException;
import com.egradebook.eGradeBook.repositories.UserRepository;
import com.egradebook.eGradeBook.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * This class is used to implement the methods that will be used to interact with the User entity.
 * The methods include creating, updating, and deleting users.
 */
@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    /**
     * This method is used to create a new user.
     * It checks if the email and phone number are not empty.
     * It also checks if the id, email, and phone number do not already exist in the database.
     * If the user is successfully created, it returns the user object.
     * If the user is not created, it throws an exception.
     * @param user The user object that will be created.
     * @return The user object that was created.
     */
    @Override
    public User registerUser(User user) {
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (user.getPhoneNumber() == null || user.getPhoneNumber().isEmpty()) {
            throw new IllegalArgumentException("Phone number is required");
        }
        //if the id or the email or the phone number already exists in the database, then throw an exception
        if(userRepository.findById(user.getId()).isPresent() == true
                || userRepository.findByEmail(user.getEmail()).isPresent() == true
                || userRepository.findByPhoneNumber(user.getPhoneNumber()).isPresent() == true)
        {  //if exists in db, then say that it exists. need function in repository
            throw new EntityAlreadyExistsException("User already exists " + user.getId() + " "+ user.getEmail() + " " + user.getPhoneNumber() + " " + user.getFirstName() + " " + user.getLastName() );
        }
        User savedUser = userRepository.save(user); //TODO: needs refining
        //set the id of the user to the id of the user that was saved
        if (savedUser == null) {
            throw new RuntimeException("User registration failed!");
        }
        return savedUser;
    }


    /**
     * This method is used to update a user.
     * It checks if the user is not null.
     * It also checks if the user exists in the database.
     * If the user is successfully updated, it returns the user object.
     * If the user is not updated, it throws an exception.
     * @param user The user object that will be updated.
     */
    @Override
    public void updateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        Optional<User> existingUser = userRepository.findById(user.getId());
        if (!existingUser.isPresent()) {
            throw new EntityNotFoundException("User does not exist");
        }
        existingUser.get().setFirstName(user.getFirstName());
        existingUser.get().setLastName(user.getLastName());
        existingUser.get().setEmail(user.getEmail());
        existingUser.get().setPhoneNumber(user.getPhoneNumber());
        existingUser.get().setPassword(user.getPassword());
        userRepository.save(existingUser.get());
    }

    /**
     * This method is used to delete a user.
     * It checks if the user exists in the database.
     * If the user is successfully deleted, it returns the user object.
     * If the user is not deleted, it throws an exception.
     * @param id The id of the user that will be deleted.
     */
    @Override
    public void deleteUser(long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new EntityNotFoundException("User not found with id " + id);
        }
        userRepository.delete(user.get());
    }

}
