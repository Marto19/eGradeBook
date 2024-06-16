package com.egradebook.eGradeBook.services.serviceImplementation;

import com.egradebook.eGradeBook.DTOs.AbsenceDTO;
import com.egradebook.eGradeBook.DTOs.GradeDTO;
import com.egradebook.eGradeBook.DTOs.classes.SchoolClassDTO;
import com.egradebook.eGradeBook.DTOs.student.StudentDTO;
import com.egradebook.eGradeBook.entities.Student;
import com.egradebook.eGradeBook.exceptions.StudentNotFoundException;
import com.egradebook.eGradeBook.exceptions.EntityAlreadyExistsException;
import com.egradebook.eGradeBook.repositories.ClassRepository;
import com.egradebook.eGradeBook.repositories.SchoolClassesRepository;
import com.egradebook.eGradeBook.repositories.StudentRepository;
import com.egradebook.eGradeBook.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;
    private final SchoolClassesRepository schoolClassesRepository;

    @Override
    public Student getStudentById(Long id) throws StudentNotFoundException {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + id));
    }

    @Override
    public Student createStudent(Student student) throws EntityAlreadyExistsException {
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new EntityAlreadyExistsException("Student already exists with email: " + student.getEmail());
        }
        // Additional logic to validate the student entity can be added here.
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student) throws StudentNotFoundException {
        if (!studentRepository.existsById(student.getId())) {
            throw new StudentNotFoundException("Cannot update. Student not found with ID: " + student.getId());
        }
        // Additional logic to validate the student entity can be added here.
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) throws StudentNotFoundException {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Cannot delete. Student not found with ID: " + id));
        studentRepository.delete(student);
    }

    @Override
    public List<Student> listAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public StudentDTO getStudentDtoById(Long id) throws StudentNotFoundException {
        return studentRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + id));
    }

    @Override
    public List<StudentDTO> getAllStudentsDto() {
        return studentRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Helper method to convert Student entity to StudentDTO
    private StudentDTO convertToDto(Student student) {
        return new StudentDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getAddress(),
                student.getEmail(),
                student.getGradeSet().stream().map(grade ->
                                new GradeDTO(grade.getId(), grade.getGrade().getId(), grade.getSubjectId().getId(), grade.getStudentId().getId(), grade.getTeacherId().getId()))
                        .collect(Collectors.toSet()),
                student.getAbsencesSet().stream().map(absence ->
                                new AbsenceDTO(absence.getId(), absence.getStudentId().getId(), absence.getSubjectId().getId(), absence.getAbsenceDate()))
                        .collect(Collectors.toSet())
        );
    }

    @Override
    public void saveOrUpdate(Student student) {
        if (student != null) {
            Student existingStudent = studentRepository.findById(student.getId()).orElse(null);
            if (existingStudent != null) {
                existingStudent.setFirstName(student.getFirstName());
                existingStudent.setLastName(student.getLastName());
                existingStudent.setAddress(student.getAddress());
                existingStudent.setEmail(student.getEmail());
                existingStudent.setPasswordHash(student.getPasswordHash());
                existingStudent.setPhoneNumber(student.getPhoneNumber());
                existingStudent.setEnabled(student.getEnabled());
                existingStudent.setRoles(student.getRoles());
                existingStudent.setParentId(student.getParentId());
                existingStudent.setSchoolId(student.getSchoolId());
                existingStudent.setClassID(student.getClassID());
                studentRepository.save(existingStudent);
            } else {
                studentRepository.save(student);
            }
        }
    }

    @Override
    public List<SchoolClassDTO> getAllClassesDto() {
        return schoolClassesRepository.findAllClassesDTO();
    }
}
