package com.egradebook.eGradeBook.services;

import com.egradebook.eGradeBook.DTOs.absence.AbsenceDTO;
import com.egradebook.eGradeBook.DTOs.absence.CreateAbsenceDTO;
import com.egradebook.eGradeBook.DTOs.absence.UpdateAbsenceDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AbsenceService
{
    void createAbsence(CreateAbsenceDTO absenceDTO);

    void updateAbsence(UpdateAbsenceDTO updateAbsenceDTO);

    void deleteAbsence(Long id);

    List<AbsenceDTO> getAllAbsenceDTOs();
}
