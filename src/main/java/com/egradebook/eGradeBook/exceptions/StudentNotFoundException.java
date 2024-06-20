package com.egradebook.eGradeBook.exceptions;

public class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String s) {
        super("Student not found");
    }
}
