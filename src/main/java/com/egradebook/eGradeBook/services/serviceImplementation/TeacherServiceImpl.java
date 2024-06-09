package com.egradebook.eGradeBook.services.serviceImplementation;

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
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
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
    public void createTeacher(Long userId, Long schoolId, List <Long> qualificationIds)
            throws UserNotFoundException, SchoolNotFoundException, QualificationNotFoundException, RoleNotFoundException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID:" + userId + " not found"));

        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new SchoolNotFoundException("School with ID:" + schoolId + " not found"));

        Role role = roleRepository.findByName("teacher")
                .orElseThrow(() -> new RoleNotFoundException("Role with name `teacher` not found"));

        user.getRoles().add(role);

        Set < Qualification > qualifications = qualificationIds.stream()
                .map(id -> {
                    try {
                        return qualificationsRepository.findById(id)
                                .orElseThrow(() -> new QualificationNotFoundException("Qualification with ID:" + id + " not found"));
                    } catch (QualificationNotFoundException qe) {
                        // TODO Handle properly
                        throw new RuntimeException(qe);
                    }
                })
                .collect(Collectors.toSet());

        Teacher teacher = new Teacher();
        teacher.setId(user.getId());
        teacher.setFirstName(user.getFirstName());
        teacher.setLastName(user.getLastName());
        teacher.setAddress(user.getAddress());
        teacher.setEmail(user.getEmail());
        teacher.setPhoneNumber(user.getPhoneNumber());
        teacher.setPasswordHash(user.getPasswordHash());
        teacher.setEnabled(user.getEnabled());
        teacher.setRoles(user.getRoles());
        teacher.setSchool(school);
        teacher.setQualificationSet(qualifications);

        teacherRepository.insertTeacher(teacher);

//        teacherRepository.insertUserRole(user.getId(), role.getId());
//
//        for (Qualification q: qualifications) {
//            teacherRepository.insertTeacherQualification(user.getId(), q.getId());
//        }
    }


}
