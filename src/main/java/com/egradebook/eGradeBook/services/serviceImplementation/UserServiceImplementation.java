package com.egradebook.eGradeBook.services.serviceImplementation;

import com.egradebook.eGradeBook.entities.User;
import com.egradebook.eGradeBook.exceptions.EntityAlreadyExistsException;
import com.egradebook.eGradeBook.repositories.UserRepository;
import com.egradebook.eGradeBook.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    //TODO: add more extensive logic or review logic
    @Override
    public User registerUser(User user)
    {
        //if the id or the email or the phone number already exists in the database, then throw an exception
        if(userRepository.findById(user.getId()).isPresent() == true
                || userRepository.findByEmail(user.getEmail()).isPresent() == true
                || userRepository.findByPhoneNumber(user.getPhoneNumber()).isPresent() == true)
        {  //if exists in db, then say that it exists. need function in repository
            //TODO: specify which field already exists
            throw new EntityAlreadyExistsException("User already exists " + user.getId() + " "+ user.getEmail() + " " + user.getPhoneNumber() + " " + user.getFirstName() + " " + user.getLastName() );
        }
        return userRepository.save(user); //TODO: needs refining
        //set the id of the user to the id of the user that was saved

    }

    //TODO: implement the rest of the methods

    @Override
    public void updateUser(User user)
    {
    }

    @Override
    public void deleteUser(long id) {

    }
}
