package com.egradebook.eGradeBook.services.serviceImplementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.egradebook.eGradeBook.DTOs.absence.AbsenceDTO;
import com.egradebook.eGradeBook.DTOs.absence.AbsenceSummaryDTO;
import com.egradebook.eGradeBook.DTOs.student.StudentDTO;
import com.egradebook.eGradeBook.DTOs.subject.SubjectDTO;
import com.egradebook.eGradeBook.entities.*;
import com.egradebook.eGradeBook.exceptions.StudentNotFoundException;
import com.egradebook.eGradeBook.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AbsenceServiceImplTest
{
    @Mock
    private AbsenceRepository absenceRepository;

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentServiceImpl studentService;

    @Mock
    private SubjectServiceImpl subjectService;

    @InjectMocks
    private AbsenceServiceImpl absenceService;

    private AbsenceDTO absenceDTO;
    private Absence absence;
    private Student student;
    private Subject subject;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");

        subject = new Subject();
        subject.setId(1L);
        subject.setName("Math");

        absence = Absence.builder()
                .id(1L)
                .studentId(student)
                .subjectId(subject)
                .absenceDate(LocalDate.now())
                .build();

        absenceDTO = new AbsenceDTO(
                1L,
                1L,
                "John Doe",
                1L,
                "Math",
                LocalDate.now()
        );
    }

    @Test
    void testCreateAbsence() throws StudentNotFoundException
    {
        when(studentRepository.findById(absenceDTO.getStudentId())).thenReturn(Optional.of(student));
        when(subjectRepository.findById(absenceDTO.getSubjectId())).thenReturn(Optional.of(subject));

        absenceService.createAbsence(absenceDTO);

        verify(absenceRepository, times(1)).save(any(Absence.class));
    }

    @Test
    void testCreateAbsence_StudentNotFound() {
        when(studentRepository.findById(absenceDTO.getStudentId())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> absenceService.createAbsence(absenceDTO));
    }

    @Test
    void testCreateAbsence_SubjectNotFound() {
        when(studentRepository.findById(absenceDTO.getStudentId())).thenReturn(Optional.of(student));
        when(subjectRepository.findById(absenceDTO.getSubjectId())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> absenceService.createAbsence(absenceDTO));
    }

    @Test
    void testUpdateAbsence() {
        when(absenceRepository.findById(absence.getId())).thenReturn(Optional.of(absence));
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        when(subjectRepository.findById(subject.getId())).thenReturn(Optional.of(subject));

        absenceService.updateAbsence(absence.getId(), student.getId(), subject.getId(), LocalDate.now());

        verify(absenceRepository, times(1)).save(any(Absence.class));
    }

    @Test
    void testUpdateAbsence_AbsenceNotFound() {
        when(absenceRepository.findById(absence.getId())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                absenceService.updateAbsence(absence.getId(), student.getId(), subject.getId(), LocalDate.now()));
    }

    @Test
    void testUpdateAbsence_StudentNotFound() {
        when(absenceRepository.findById(absence.getId())).thenReturn(Optional.of(absence));
        when(studentRepository.findById(student.getId())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                absenceService.updateAbsence(absence.getId(), student.getId(), subject.getId(), LocalDate.now()));
    }

    @Test
    void testUpdateAbsence_SubjectNotFound() {
        when(absenceRepository.findById(absence.getId())).thenReturn(Optional.of(absence));
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        when(subjectRepository.findById(subject.getId())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                absenceService.updateAbsence(absence.getId(), student.getId(), subject.getId(), LocalDate.now()));
    }

    @Test
    void testDeleteAbsence() {
        absenceService.deleteAbsence(absence.getId());

        verify(absenceRepository, times(1)).deleteById(absence.getId());
    }

    @Test
    void testGetAllAbsenceDTOs() {
        when(absenceRepository.findAll()).thenReturn(Arrays.asList(absence));

        List<AbsenceDTO> absenceDTOs = absenceService.getAllAbsenceDTOs();

        assertFalse(absenceDTOs.isEmpty());
        assertEquals(1, absenceDTOs.size());
        assertEquals(absence.getId(), absenceDTOs.get(0).getId());
    }

    @Test
    void testGetAllStudents()
    {
        List<StudentDTO> studentDTOs = Arrays.asList(new StudentDTO());

        when(studentService.getAllStudentsDto()).thenReturn(studentDTOs);

        List<StudentDTO> result = absenceService.getAllStudents();

        assertEquals(studentDTOs, result);
    }

    @Test
    void testGetAllSubjects()
    {
        List<SubjectDTO> subjectDTOs = Arrays.asList(new SubjectDTO());

        when(subjectService.getAllSubjectsDto()).thenReturn(subjectDTOs);

        List<SubjectDTO> result = absenceService.getAllSubjects();

        assertEquals(subjectDTOs, result);
    }

    @Test
    void testGetStudentAbsenceDTOsById()
    {
        List<AbsenceSummaryDTO> summaryDTOs = Arrays.asList(new AbsenceSummaryDTO());

        when(absenceRepository.getStudentAbsenceDTOsById(student.getId())).thenReturn(summaryDTOs);

        List<AbsenceSummaryDTO> result = absenceService.getStudentAbsenceDTOsById(student.getId());

        assertEquals(summaryDTOs, result);
    }

    @Test
    void testFindAbsenceById() {
        when(absenceRepository.findById(absence.getId())).thenReturn(Optional.of(absence));

        Absence foundAbsence = absenceService.findAbsenceById(absence.getId());

        assertEquals(absence, foundAbsence);
    }

    @Test
    void testFindAbsenceById_NotFound() {
        when(absenceRepository.findById(absence.getId())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> absenceService.findAbsenceById(absence.getId()));
    }
}