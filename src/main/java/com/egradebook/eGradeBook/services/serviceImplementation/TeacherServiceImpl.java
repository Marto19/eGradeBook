package com.egradebook.eGradeBook.services.serviceImplementation;

import com.egradebook.eGradeBook.DTOs.teacher.TeacherCreateDTO;
import com.egradebook.eGradeBook.DTOs.teacher.TeacherDTO;
import com.egradebook.eGradeBook.entities.Qualification;
import com.egradebook.eGradeBook.entities.Role;
import com.egradebook.eGradeBook.entities.School;
import com.egradebook.eGradeBook.entities.Teacher;
import com.egradebook.eGradeBook.entities.User;
import com.egradebook.eGradeBook.exceptions.QualificationNotFoundException;
import com.egradebook.eGradeBook.exceptions.SchoolNotFoundException;
import com.egradebook.eGradeBook.exceptions.TeacherNotFoundException;
import com.egradebook.eGradeBook.exceptions.UserNotFoundException;
import com.egradebook.eGradeBook.repositories.QualificationsRepository;
import com.egradebook.eGradeBook.repositories.RoleRepository;
import com.egradebook.eGradeBook.repositories.SchoolRepository;
import com.egradebook.eGradeBook.repositories.TeacherRepository;
import com.egradebook.eGradeBook.repositories.UserRepository;
import com.egradebook.eGradeBook.services.TeacherService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final UserRepository userRepository;
    private final SchoolRepository schoolRepository;
    private final QualificationsRepository qualificationsRepository;
    private final TeacherRepository teacherRepository;
    private final RoleRepository roleRepository;


    public TeacherServiceImpl(UserRepository userRepository, SchoolRepository schoolRepository, QualificationsRepository qualificationsRepository, TeacherRepository teacherRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.schoolRepository = schoolRepository;
        this.qualificationsRepository = qualificationsRepository;
        this.teacherRepository = teacherRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<TeacherDTO> getAllTeachersDto() {
        return teacherRepository.getTeachersDTO();
    }

    @Override
    public Teacher updateTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacher(Long userId) throws TeacherNotFoundException {
        Teacher teacher = teacherRepository.findById(userId)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher with ID:" + userId + " not found!"));

//        teacher.setSchool(null);
//        teacher.setQualificationSet(null);

        teacherRepository.deleteById(teacher.getId());
    }

    @Override
    @Transactional
    public void createTeacher(Long userId, Long schoolId, Collection<Long> qualificationIds) {

        User user = userRepository.findById(userId).orElseThrow();
        School school = schoolRepository.findById(schoolId).orElseThrow();

        Set<Qualification> qualifications = qualificationIds.stream()
                .map(id -> qualificationsRepository.findById(id).orElseThrow())
                .collect(Collectors.toSet());

        Role teacherRole = roleRepository.findByName("teacher").orElseThrow();
        user.getRoles().add(teacherRole);

        Teacher teacher = new Teacher();
        teacher.setId(userId);
        teacher.setFirstName(user.getFirstName());
        teacher.setLastName(user.getLastName());
        teacher.setAddress(user.getAddress());
        teacher.setEmail(user.getEmail());
        teacher.setPasswordHash(user.getPasswordHash());
        teacher.setPhoneNumber(user.getPhoneNumber());
        teacher.setEnabled(user.getEnabled());
        teacher.setRoles(user.getRoles());

        teacher.setSchool(school);

        teacherRepository.insertTeacher(userId, schoolId);

        for (Long qualificationId : qualificationIds) {
            teacherRepository.insertTeacherQualification(userId, qualificationId);
        }

    }


    @Override
    public Teacher findById(Long id) throws TeacherNotFoundException {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher with ID:" + id + " not found!"));
    }

    @Override
    public TeacherDTO findTeacherDTOById(Long id) {
        return teacherRepository.findTeacherDTOById(id);
    }


}
