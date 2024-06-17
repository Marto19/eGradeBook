package com.egradebook.eGradeBook.services.serviceImplementation;

import com.egradebook.eGradeBook.DTOs.absence.AbsenceDTO;
import com.egradebook.eGradeBook.DTOs.absence.CreateAbsenceDTO;
import com.egradebook.eGradeBook.DTOs.absence.UpdateAbsenceDTO;
import com.egradebook.eGradeBook.repositories.AbsenceRepository;
import com.egradebook.eGradeBook.repositories.SubjectRepository;
import com.egradebook.eGradeBook.services.AbsenceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AbsenceServiceImpl implements AbsenceService
{
    private final AbsenceRepository absenceRepository;

    private final SubjectRepository subjectRepository;

    @Override
    public void createAbsence(CreateAbsenceDTO absenceDTO)
    {
        //ADD CHECK FOR STUDENT WHEN FUNCTIONALITY IS PRESENT
    }

    @Override
    public void updateAbsence(UpdateAbsenceDTO updateAbsenceDTO) {

    }

    @Override
    public void deleteAbsence(Long id) {

    }

    @Override
    public List<AbsenceDTO> getAllAbsenceDTOs() {
        return null;
    }
}
