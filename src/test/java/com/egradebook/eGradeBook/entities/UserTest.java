package com.egradebook.eGradeBook.entities;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validator;


import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserTest
{
    private Validator validator;

    @BeforeEach
    public void setUp()
    {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        validator = factory.getValidator();
    }

    @Test
    void testValidUser()
    {
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .email("johndoe@example.com")
                .passwordHash("passwordHash")
                .phoneNumber("1234567890")
                .enabled(true)
                .roles(null) // or some valid roles set
                .build();

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            violations.forEach(violation -> System.out.println(violation.getPropertyPath() + ": " + violation.getMessage()));
        }
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidFirstName()
    {
        User user = User.builder()
                .firstName("John123")
                .lastName("Doe")
                .address("123 Main St")
                .email("johndoe@example.com")
                .passwordHash("passwordHash")
                .phoneNumber("1234567890")
                .enabled(true)
                .roles(null)
                .build();

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("firstName")));
    }

    @Test
    void testInvalidEmail()
    {
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .email("john.doe@example.com") // Invalid email
                .passwordHash("passwordHash")
                .phoneNumber("1234567890")
                .enabled(true)
                .roles(null)
                .build();

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("email")));
    }

    @Test
    void testInvalidPhoneNumber()
    {
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .email("john.doe@example.com")
                .passwordHash("passwordHash")
                .phoneNumber("12345abc") // Invalid phone number
                .enabled(true)
                .roles(null)
                .build();

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("phoneNumber")));
    }

    @Test
    void testBuilder() {
        // Arrange
        long userId = 42L;
        String firstName = "John";
        String lastName = "Doe";
        String address = "123 Main St";
        String email = "johndoe@example.com";
        String passwordHash = "passwordHash";
        String phoneNumber = "1234567890";
        boolean enabled = true;

        // Act
        User user = User.builder()
                .id(userId)
                .firstName(firstName)
                .lastName(lastName)
                .address(address)
                .email(email)
                .passwordHash(passwordHash)
                .phoneNumber(phoneNumber)
                .enabled(enabled)
                .roles(null)
                .build();

        // Assert
        assertEquals(userId, user.getId());
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(address, user.getAddress());
        assertEquals(email, user.getEmail());
        assertEquals(passwordHash, user.getPasswordHash());
        assertEquals(phoneNumber, user.getPhoneNumber());
        assertEquals(enabled, user.getEnabled());
    }
}