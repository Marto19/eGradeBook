package com.egradebook.eGradeBook.services.serviceImplementation;

import com.egradebook.eGradeBook.DTOs.AbsenceDTO;
import com.egradebook.eGradeBook.DTOs.GradeDTO;
import com.egradebook.eGradeBook.DTOs.classes.SchoolClassDTO;
import com.egradebook.eGradeBook.DTOs.student.StudentDTO;
import com.egradebook.eGradeBook.DTOs.student.StudentSummaryDTO;
import com.egradebook.eGradeBook.entities.Class;
import com.egradebook.eGradeBook.entities.Grade;
import com.egradebook.eGradeBook.entities.Parent;
import com.egradebook.eGradeBook.entities.Role;
import com.egradebook.eGradeBook.entities.School;
import com.egradebook.eGradeBook.entities.Student;
import com.egradebook.eGradeBook.entities.User;
import com.egradebook.eGradeBook.exceptions.StudentNotFoundException;
import com.egradebook.eGradeBook.exceptions.EntityAlreadyExistsException;
import com.egradebook.eGradeBook.repositories.ClassRepository;
import com.egradebook.eGradeBook.repositories.ParentRepository;
import com.egradebook.eGradeBook.repositories.RoleRepository;
import com.egradebook.eGradeBook.repositories.SchoolClassesRepository;
import com.egradebook.eGradeBook.repositories.SchoolRepository;
import com.egradebook.eGradeBook.repositories.StudentRepository;
import com.egradebook.eGradeBook.repositories.TeacherRepository;
import com.egradebook.eGradeBook.repositories.UserRepository;
import com.egradebook.eGradeBook.services.StudentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;
    private final SchoolClassesRepository schoolClassesRepository;
    private final ParentRepository parentRepository;
    private final SchoolRepository schoolRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TeacherRepository teacherRepository;

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
//    private StudentDTO convertToDto(Student student) {
//        return new StudentDTO(
//                student.getId(),
//                student.getFirstName(),
//                student.getLastName(),
//                student.getAddress(),
//                student.getEmail(),
//                student.getGradeSet().stream().map(grade ->
//                                new GradeDTO(grade.getId(), grade.getGrade().getId(), grade.getSubjectId().getId(), grade.getStudentId().getId(), grade.getTeacherId().getId()))
//                        .collect(Collectors.toSet()),
//                student.getAbsenceSet().stream().map(absence ->
//                                new AbsenceDTO(absence.getId(), absence.getStudentId().getId(), absence.getSubjectId().getId(), absence.getAbsenceDate()))
//                        .collect(Collectors.toSet())
//        );
//    }
    private StudentDTO convertToDto(Student student) {
        Set<GradeDTO> gradeDTOs = student.getGradeSet().stream()
                .map(grade -> new GradeDTO(grade.getId(), grade.getGrade().getId(), grade.getSubjectId().getId(), grade.getStudentId().getId(), grade.getTeacherId().getId()))
                .collect(Collectors.toSet());

        Set<AbsenceDTO> absenceDTOs = student.getAbsenceSet().stream()
                .map(absence -> new AbsenceDTO(absence.getId(), absence.getStudentId().getId(), absence.getSubjectId().getId(), absence.getAbsenceDate()))
                .collect(Collectors.toSet());

        return new StudentDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getAddress(),
                student.getEmail(),
                gradeDTOs,
                absenceDTOs
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


    @Override
    @Transactional
    public Student createStudent(Long userId, Long classId, Long parentId, Long schoolId) throws RoleNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        Class currClass = classRepository.findById(classId).orElseThrow(EntityNotFoundException::new);
        Parent parent = parentRepository.findById(parentId).orElseThrow(EntityNotFoundException::new);
        School school = schoolRepository.findById(schoolId).orElseThrow(EntityNotFoundException::new);

        Role studentRole = roleRepository.findByName("student")
                .orElseThrow(() -> new RoleNotFoundException("Role student was not found"));

        user.getRoles().add(studentRole);

        Student student = new Student();
        student.setId(userId);
        student.setFirstName(user.getFirstName());
        student.setLastName(user.getLastName());
        student.setAddress(user.getAddress());
        student.setEmail(user.getEmail());
        student.setPasswordHash(user.getPasswordHash());
        student.setPhoneNumber(user.getPhoneNumber());
        student.setEnabled(user.getEnabled());
        student.setRoles(user.getRoles());

        student.setClassID(currClass);
        student.setParentId(parent);
        student.setSchoolId(school);

        studentRepository.insertStudent(userId, classId, parentId, schoolId);

        return student;
    }

    @Override
    public List<StudentSummaryDTO> findStudentsByTeacherId(Long teacherId) {
        return studentRepository.findStudentsByTeacherId(teacherId);
    }
}
