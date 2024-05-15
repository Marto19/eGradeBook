package com.egradebook.eGradeBook.services;

import com.egradebook.eGradeBook.entities.User;
import org.springframework.stereotype.Service;

/**
 * This interface is used to define the methods that will be used to interact with the User entity.
 */
public interface UserService {
    /**
     * This method is used to create a new user.
     * @param user The user object that will be created.
     * @return The user object that was created.
     */
    public User registerUser(User user);

    /**
     * This method is used to update a user.
     * @param user The user object that will be updated.
     * @return The user object that was updated.
     */
    public void updateUser(User user);

    /**
     * This method is used to delete a user.
     * @param id The id of the user that will be deleted.
     */
    public void deleteUser(long id);
}
