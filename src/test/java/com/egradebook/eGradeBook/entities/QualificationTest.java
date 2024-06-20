package com.egradebook.eGradeBook.entities;

import com.egradebook.eGradeBook.exceptions.QualificationNotFoundException;
import com.egradebook.eGradeBook.repositories.QualificationsRepository;
import com.egradebook.eGradeBook.services.serviceImplementation.QualificationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QualificationTest {

    @Mock
    private QualificationsRepository qualificationsRepository;

    @InjectMocks
    private QualificationServiceImpl qualificationService;

    @Test
    void testGetQualificationById_ExistingId() throws QualificationNotFoundException {
        // Arrange
        long qualificationId = 1L;
        Qualification expectedQualification = new Qualification(qualificationId, "Sig1", "Desc1", null);
        when(qualificationsRepository.findById(qualificationId)).thenReturn(Optional.of(expectedQualification));

        // Act
        Qualification actualQualification = qualificationService.getQualificationById(qualificationId);

        // Assert
        assertEquals(expectedQualification, actualQualification);
        verify(qualificationsRepository).findById(qualificationId);
    }

    @Test
    void testGetQualificationById_NonExistingId() {
        // Arrange
        long nonExistingId = 999L;
        when(qualificationsRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(QualificationNotFoundException.class, () -> qualificationService.getQualificationById(nonExistingId));
        verify(qualificationsRepository).findById(nonExistingId);
    }

    @Test
    void getId() {
        // Arrange
        long qualificationId = 42L;
        Qualification qualification = new Qualification(qualificationId, "Sig", "Desc", null);

        // Act
        long actualId = qualification.getId();

        // Assert
        assertEquals(qualificationId, actualId);
    }

    @Test
    void getSignature() {
        // Arrange
        String expectedSignature = "Sig";
        Qualification qualification = new Qualification(42L, expectedSignature, "Desc", null);

        // Act
        String actualSignature = qualification.getSignature();

        // Assert
        assertEquals(expectedSignature, actualSignature);
    }

    @Test
    void getDescription() {
        // Arrange
        String expectedDescription = "Description";
        Qualification qualification = new Qualification(42L, "Sig", expectedDescription, null);

        // Act
        String actualDescription = qualification.getDescription();

        // Assert
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    void getTeacherSet() {
        // Arrange
        Set<Teacher> expectedTeachers = Set.of(new Teacher(), new Teacher());
        Qualification qualification = new Qualification(42L, "Sig", "Desc", expectedTeachers);

        // Act
        Set<Teacher> actualTeachers = qualification.getTeacherSet();

        // Assert
        assertEquals(expectedTeachers, actualTeachers);
    }

    @Test
    void setId() {
        // Arrange
        long newId = 99L;
        Qualification qualification = new Qualification(42L, "Sig", "Desc", null);

        // Act
        qualification.setId(newId);

        // Assert
        assertEquals(newId, qualification.getId());
    }

    @Test
    void setSignature() {
        // Arrange
        String initialSignature = "InitialSig";
        Qualification qualification = new Qualification(42L, initialSignature, "Desc", null);

        // Act
        String newSignature = "NewSig";
        qualification.setSignature(newSignature);

        // Assert
        assertEquals(newSignature, qualification.getSignature());
    }

    @Test
    void setDescription() {
        // Arrange
        String initialDescription = "Initial description";
        Qualification qualification = new Qualification(42L, "Sig", initialDescription, null);

        // Act
        String newDescription = "New description";
        qualification.setDescription(newDescription);

        // Assert
        assertEquals(newDescription, qualification.getDescription());
    }

    @Test
    void setTeacherSet() {
        // Arrange
        Set<Teacher> initialTeachers = Set.of(new Teacher(), new Teacher());
        Qualification qualification = new Qualification(42L, "Sig", "Desc", initialTeachers);

        // Act
        Set<Teacher> newTeachers = Set.of(new Teacher());
        qualification.setTeacherSet(newTeachers);

        // Assert
        assertEquals(newTeachers, qualification.getTeacherSet());
    }

    @Test
    void builder() {
        // Arrange
        long qualificationId = 42L;
        String signature = "Sig";
        String description = "Desc";
        Set<Teacher> teachers = Set.of(new Teacher());

        // Act
        Qualification qualification = Qualification.builder()
                .id(qualificationId)
                .signature(signature)
                .description(description)
                .teacherSet(teachers)
                .build();

        // Assert
        assertEquals(qualificationId, qualification.getId());
        assertEquals(signature, qualification.getSignature());
        assertEquals(description, qualification.getDescription());
        assertEquals(teachers, qualification.getTeacherSet());
    }
}