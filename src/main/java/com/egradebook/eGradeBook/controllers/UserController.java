package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.entities.User;
import com.egradebook.eGradeBook.exceptions.ConflictException;
import com.egradebook.eGradeBook.repositories.UserRepository;
import com.egradebook.eGradeBook.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * This class is used to create the controller for the User entity.
 * It contains methods that will be used to interact with the User entity.
 * The methods include creating, updating, deleting, and getting users.
 *
 */
@Validated
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    /**
     * This method is used to create a new user.
     * @param user The user object that will be created.
     * @return The user object that was created.
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user)
    {
        User registeredUser = userService.registerUser(user);// Adjust your service method accordingly

        if (registeredUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("User registration failed!");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User registered successfully with ID: " + registeredUser.getId());
        //if didnt register successfully, return bad request with message

    }

    /**
     * This method is used to get a user by id.
     * @param id The id of the user that will be retrieved.
     * @return The user object that was retrieved.
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable long id)
    {
        Optional<User> user = userRepository.findUsersById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * This method is used to get all users.
     * @return The list of all users.
     */
    @GetMapping("/get")
    public ResponseEntity<Iterable<User>> getUsers()
    {
        Iterable<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    /**
     * This method is used to update a user.
     * @param user The user object that will be updated.
     * @return The user object that was updated.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@Valid @RequestBody User user)
    {
        //use find user by id to get the user
        Optional<User> userOptional = userRepository.findUsersById(user.getId());
        if(userOptional.isPresent())
        {
            userService.updateUser(user);
            return ResponseEntity.ok("User updated successfully");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    /**
     * This method is used to delete a user.
     * @param id The id of the user that will be deleted.
     *           @return The user object that was deleted.
     *           @throws ConflictException if the user has any dependencies
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id)
    {
        //use find user by id to get the user
        Optional<User> userOptional = userRepository.findUsersById(id);
        if(userOptional.isPresent())
        {
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        }
        //TODO: replace with the acutal methods that check if the user has any dependencies
//        else if (!userOptional.getTeachers().isEmpty() || !userOptional.getPrincipals().isEmpty() || !user.getParents().isEmpty() || !user.getStudents().isEmpty()) {
//            throw new ConflictException("Cannot delete user with id " + id + " because it has dependent entities");
//        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}
