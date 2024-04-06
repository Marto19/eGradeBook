package com.egradebook.eGradeBook.services;

import com.egradebook.eGradeBook.entities.User;
import com.egradebook.eGradeBook.exceptions.EntityAlreadyExistsException;
import com.egradebook.eGradeBook.repositories.UserRepository;

public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        if(userRepository.existsById(user.getId())){  //if exists in db, then say that it exists. need function in repository
            throw new EntityAlreadyExistsException("User with id " + user.getId() + " already exists");
        }
        return userRepository.save(user); //TODO: needs refining
    }
}
