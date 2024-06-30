package com.egradebook.eGradeBook.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AbsenceTest
{
    @Test
    void testGetId() {
        // Arrange
        long id = 1L;
        Absence absence = new Absence();
        absence.setId(id);

        // Act
        long actualId = absence.getId();

        // Assert
        assertEquals(id, actualId);
    }

    @Test
    void testGetStudentId() {
        // Arrange
        Student student = new Student();
        Absence absence = new Absence();
        absence.setStudentId(student);

        // Act
        Student actualStudent = absence.getStudentId();

        // Assert
        assertEquals(student, actualStudent);
    }

    @Test
    void testGetSubjectId() {
        // Arrange
        Subject subject = new Subject();
        Absence absence = new Absence();
        absence.setSubjectId(subject);

        // Act
        Subject actualSubject = absence.getSubjectId();

        // Assert
        assertEquals(subject, actualSubject);
    }

    @Test
    void testGetAbsenceDate() {
        // Arrange
        LocalDate absenceDate = LocalDate.now();
        Absence absence = new Absence();
        absence.setAbsenceDate(absenceDate);

        // Act
        LocalDate actualDate = absence.getAbsenceDate();

        // Assert
        assertEquals(absenceDate, actualDate);
    }

    @Test
    void testSetId() {
        // Arrange
        long newId = 99L;
        Absence absence = new Absence();
        absence.setId(newId);

        // Act
        long actualId = absence.getId();

        // Assert
        assertEquals(newId, actualId);
    }

    @Test
    void testSetStudentId() {
        // Arrange
        Student student = new Student();
        Absence absence = new Absence();
        absence.setStudentId(student);

        // Act
        Student actualStudent = absence.getStudentId();

        // Assert
        assertEquals(student, actualStudent);
    }

    @Test
    void testSetSubjectId() {
        // Arrange
        Subject subject = new Subject();
        Absence absence = new Absence();
        absence.setSubjectId(subject);

        // Act
        Subject actualSubject = absence.getSubjectId();

        // Assert
        assertEquals(subject, actualSubject);
    }

    @Test
    void testSetAbsenceDate() {
        // Arrange
        LocalDate absenceDate = LocalDate.now();
        Absence absence = new Absence();
        absence.setAbsenceDate(absenceDate);

        // Act
        LocalDate actualDate = absence.getAbsenceDate();

        // Assert
        assertEquals(absenceDate, actualDate);
    }

    @Test
    void testConstructor() {
        // Arrange
        long id = 1L;
        Student student = new Student();
        Subject subject = new Subject();
        LocalDate absenceDate = LocalDate.now();

        // Act
        Absence absence = new Absence(id, student, subject, absenceDate);

        // Assert
        assertEquals(id, absence.getId());
        assertEquals(student, absence.getStudentId());
        assertEquals(subject, absence.getSubjectId());
        assertEquals(absenceDate, absence.getAbsenceDate());
    }

    @Test
    void testBuilder() {
        // Arrange
        long id = 1L;
        Student student = new Student();
        Subject subject = new Subject();
        LocalDate absenceDate = LocalDate.now();

        // Act
        Absence absence = Absence.builder()
                .id(id)
                .studentId(student)
                .subjectId(subject)
                .absenceDate(absenceDate)
                .build();

        // Assert
        assertEquals(id, absence.getId());
        assertEquals(student, absence.getStudentId());
        assertEquals(subject, absence.getSubjectId());
        assertEquals(absenceDate, absence.getAbsenceDate());
    }
}