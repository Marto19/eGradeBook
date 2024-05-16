package com.egradebook.eGradeBook.services.serviceImplementation;

import com.egradebook.eGradeBook.entities.User;
import com.egradebook.eGradeBook.exceptions.EntityAlreadyExistsException;
import com.egradebook.eGradeBook.repositories.UserRepository;
import com.egradebook.eGradeBook.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

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


    @Override
    public void updateUser(User user) {
        //if user is null, throw an exception
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        //if user does not exist, throw an exception
        Optional<User> existingUser = userRepository.findById(user.getId());
        if (!existingUser.isPresent()) {
            throw new EntityNotFoundException("User not found with id " + user.getId());
        }
        // update fields
        existingUser.get().setEmail(user.getEmail());
        existingUser.get().setPhoneNumber(user.getPhoneNumber());
        // add other fields that you want to update

        // save and return the updated user
        userRepository.save(existingUser.get());
    }

    @Override
    public void deleteUser(long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new EntityNotFoundException("User not found with id " + id);
        }
        userRepository.delete(user.get());
    }

}
