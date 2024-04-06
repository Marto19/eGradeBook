package com.egradebook.eGradeBook.services;

import org.springframework.security.core.userdetails.User;

public interface UserService {
    public User createUser(User user);
}
