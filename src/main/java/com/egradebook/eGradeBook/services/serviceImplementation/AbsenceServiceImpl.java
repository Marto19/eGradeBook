package com.egradebook.eGradeBook.services.serviceImplementation;

import com.egradebook.eGradeBook.DTOs.absence.AbsenceDTO;
import com.egradebook.eGradeBook.DTOs.absence.CreateAbsenceDTO;
import com.egradebook.eGradeBook.DTOs.absence.UpdateAbsenceDTO;
import com.egradebook.eGradeBook.entities.Absence;
import com.egradebook.eGradeBook.entities.Subject;
import com.egradebook.eGradeBook.exceptions.InvalidAbsenceException;
import com.egradebook.eGradeBook.exceptions.InvalidSubjectException;
import com.egradebook.eGradeBook.repositories.AbsenceRepository;
import com.egradebook.eGradeBook.repositories.SubjectRepository;
import com.egradebook.eGradeBook.services.AbsenceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AbsenceServiceImpl implements AbsenceService
{
    private final AbsenceRepository absenceRepository;

    private final SubjectRepository subjectRepository;

    @Override
    public void createAbsence(CreateAbsenceDTO absenceDTO)
    {
        if(!subjectRepository.existsById(absenceDTO.getSubjectId()))
        {
            throw new InvalidSubjectException("Subject does not exist");
        }
        else if (absenceDTO.getSubjectId() == null /*add check for student when functionality is present*/)
        {
            //Throw new INVALID STUDENT
        }
        else
        {
            //Student student = studentRepository...

            Subject subject = subjectRepository.findById(absenceDTO.getSubjectId()).orElseThrow(() -> new InvalidSubjectException("Invalid subject."));

            Absence absence = Absence.builder()
                    .student(null) //CHANGE WHEN STUDENT FUNCTIONALITY IS PRESENT
                    .subject(subject)
                    .absenceDate(LocalDate.now())
                    .build();

            absenceRepository.save(absence);
        }
    }

    @Override
    public void updateAbsence(UpdateAbsenceDTO updateAbsenceDTO)
    {
        if(!subjectRepository.existsById(updateAbsenceDTO.getSubjectId()))
        {
            throw new InvalidSubjectException("Subject does not exist");
        }
        else if (updateAbsenceDTO.getSubjectId() == null /*add check for student when functionality is present*/)
        {
            //Throw new INVALID STUDENT
        }
        else
        {
            Absence existingAbsence = absenceRepository.findById(updateAbsenceDTO.getId())
                    .orElseThrow(() -> new InvalidAbsenceException("Invalid absence."));

            existingAbsence.setStudent(null); //complete when student is implemented

            existingAbsence.setSubject(subjectRepository.findById(updateAbsenceDTO.getSubjectId())
                    .orElseThrow(() -> new InvalidSubjectException("Invalid subject.")));

            existingAbsence.setAbsenceDate(updateAbsenceDTO.getAbsenceDate());

            absenceRepository.save(existingAbsence);
        }
    }

    @Override
    public void deleteAbsence(Long id)
    {
        //CHECK IF DELETE AS IS WON'T DELETE THE CONNECTED STUDENT AND SUBJECT

        Absence existinAbsence = absenceRepository.findById(id)
                .orElseThrow(() -> new InvalidAbsenceException("Invalid Absence"));

            absenceRepository.delete(existinAbsence);
    }

    @Override
    public List<AbsenceDTO> getAllAbsenceDTOs()
    {
        List<Absence> absences = absenceRepository.findAll();

        return absences.stream()
                .map(absence -> new AbsenceDTO(
                        absence.getId(),
                        absence.getStudent(),
                        absence.getSubject(),
                        absence.getAbsenceDate()))
                .collect(Collectors.toList());
    }
}
