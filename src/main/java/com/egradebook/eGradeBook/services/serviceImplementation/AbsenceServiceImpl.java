package com.egradebook.eGradeBook.services.serviceImplementation;

import com.egradebook.eGradeBook.DTOs.absence.AbsenceDTO;
import com.egradebook.eGradeBook.DTOs.absence.AbsenceSummaryDTO;
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
import jakarta.persistence.EntityNotFoundException;
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
    public void createAbsence(AbsenceDTO absenceDTO) throws StudentNotFoundException {
        Absence absence = new Absence();
        absence.setStudentId(studentRepository.findById(absenceDTO.getStudentId()).orElseThrow(() -> new RuntimeException("Student not found")));
        absence.setSubjectId(subjectRepository.findById(absenceDTO.getSubjectId()).orElseThrow(() -> new RuntimeException("Subject not found")));
        absence.setAbsenceDate(absenceDTO.getAbsenceDate());
        absenceRepository.save(absence);
    }

    @Override
    public void updateAbsence(Long absenceId, Long studentId, Long subjectId, LocalDate absenceDate) {
        Absence absence = absenceRepository.findById(absenceId)
                .orElseThrow(() -> new EntityNotFoundException("Absence not found"));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found"));

        absence.setStudentId(student);
        absence.setSubjectId(subject);
        absence.setAbsenceDate(absenceDate);

        absenceRepository.save(absence);
    }

    @Override
    public void deleteAbsence(Long id)
    {
        absenceRepository.deleteById(id);
    }

    @Override
    public List<AbsenceDTO> getAllAbsenceDTOs() {
        List<Absence> absences = absenceRepository.findAll();

        return absences.stream()
                .map(absence -> new AbsenceDTO(
                        absence.getId(),
                        absence.getStudentId().getId(),
                        absence.getStudentId().getFirstName() + " " + absence.getStudentId().getLastName(), // Get the full name
                        absence.getSubjectId().getId(),
                        absence.getSubjectId().getName(), // Get the subject name
                        absence.getAbsenceDate()
                ))
                .collect(Collectors.toList());
    }

    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudentsDto();
    }

    public List<SubjectDTO> getAllSubjects() {
        return subjectService.getAllSubjectsDto();
    }

    @Override
    public List<AbsenceSummaryDTO> getStudentAbsenceDTOsById(Long studentId) {
        return absenceRepository.getStudentAbsenceDTOsById(studentId);
    }

    @Override
    public Absence findAbsenceById(Long id) {
        return absenceRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
