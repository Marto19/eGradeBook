package com.egradebook.eGradeBook.services;

import com.egradebook.eGradeBook.DTOs.absence.AbsenceDTO;
import com.egradebook.eGradeBook.DTOs.absence.CreateAbsenceDTO;
import com.egradebook.eGradeBook.DTOs.absence.UpdateAbsenceDTO;
import com.egradebook.eGradeBook.DTOs.student.StudentDTO;
import com.egradebook.eGradeBook.DTOs.subject.SubjectDTO;
import com.egradebook.eGradeBook.entities.Student;
import com.egradebook.eGradeBook.entities.Subject;
import com.egradebook.eGradeBook.exceptions.StudentNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AbsenceService
{
    void createAbsence(CreateAbsenceDTO absenceDTO) throws StudentNotFoundException;

    void updateAbsence(UpdateAbsenceDTO updateAbsenceDTO) throws StudentNotFoundException;

    void deleteAbsence(Long id);

    List<AbsenceDTO> getAllAbsenceDTOs();

    List<StudentDTO> getAllStudents();

    List<SubjectDTO> getAllSubjects();
}
