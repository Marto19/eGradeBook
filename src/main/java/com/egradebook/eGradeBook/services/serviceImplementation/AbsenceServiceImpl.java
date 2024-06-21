package com.egradebook.eGradeBook.services.serviceImplementation;

import com.egradebook.eGradeBook.DTOs.absence.AbsenceDTO;
import com.egradebook.eGradeBook.DTOs.absence.CreateAbsenceDTO;
import com.egradebook.eGradeBook.DTOs.absence.UpdateAbsenceDTO;
import com.egradebook.eGradeBook.DTOs.student.StudentDTO;
import com.egradebook.eGradeBook.DTOs.subject.SubjectDTO;
import com.egradebook.eGradeBook.entities.Absence;
import com.egradebook.eGradeBook.entities.Student;
import com.egradebook.eGradeBook.entities.Subject;
import com.egradebook.eGradeBook.exceptions.StudentNotFoundException;
import com.egradebook.eGradeBook.repositories.AbsenceRepository;
import com.egradebook.eGradeBook.repositories.StudentRepository;
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

    private final StudentRepository studentRepository;

    private final StudentServiceImpl studentService;

    private final SubjectServiceImpl subjectService;

    @Override
    public void createAbsence(AbsenceDTO absenceDTO) throws StudentNotFoundException
    {
        Absence absence = new Absence();
        absence.setStudentId(studentRepository.findById(absenceDTO.getStudentId().getId()).orElseThrow(() -> new RuntimeException("Student not found")));
        absence.setSubjectId(subjectRepository.findById(absenceDTO.getSubjectId().getId()).orElseThrow(() -> new RuntimeException("Subject not found")));
        absence.setAbsenceDate(absenceDTO.getAbsenceDate());
        absenceRepository.save(absence);
    }

    @Override
    public void updateAbsence(UpdateAbsenceDTO updateAbsenceDTO) throws StudentNotFoundException
    {
        //UPDATE ABSENCE DTO
    }

    @Override
    public void deleteAbsence(Long id)
    {

    }

    @Override
    public List<AbsenceDTO> getAllAbsenceDTOs()
    {
        List<Absence> absences = absenceRepository.findAll();

        return absences.stream()
                .map(absence -> new AbsenceDTO(
                        absence.getId(),
                        absence.getStudentId(),
                        absence.getSubjectId(),
                        absence.getAbsenceDate()))
                .collect(Collectors.toList());
    }

//    @Override
//    public List<Student> getAllStudents()
//    {
//        return null;
//    }
//
//    @Override
//    public List<Subject> getAllSubjects() {
//        return null;
//    }

    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudentsDto();
    }

    public List<SubjectDTO> getAllSubjects() {
        return subjectService.getAllSubjectsDto();
    }

}
