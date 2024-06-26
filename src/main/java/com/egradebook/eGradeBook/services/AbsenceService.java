package com.egradebook.eGradeBook.services;

import com.egradebook.eGradeBook.DTOs.absence.AbsenceDTO;
import com.egradebook.eGradeBook.DTOs.absence.AbsenceSummaryDTO;
import com.egradebook.eGradeBook.DTOs.absence.UpdateAbsenceDTO;
import com.egradebook.eGradeBook.DTOs.student.StudentDTO;
import com.egradebook.eGradeBook.DTOs.subject.SubjectDTO;
import com.egradebook.eGradeBook.entities.Absence;
import com.egradebook.eGradeBook.exceptions.StudentNotFoundException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface AbsenceService
{
    void createAbsence(AbsenceDTO absenceDTO) throws StudentNotFoundException;

    void updateAbsence(Long absenceId, Long studentId, Long subjectId, LocalDate absenceDate);

    void deleteAbsence(Long id);

    List<AbsenceDTO> getAllAbsenceDTOs();

    List<StudentDTO> getAllStudents();

    List<SubjectDTO> getAllSubjects();

    List<AbsenceSummaryDTO> getStudentAbsenceDTOsById(Long studentId);

    Absence findAbsenceById(Long id);

}
