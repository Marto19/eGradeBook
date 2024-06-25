package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.DTOs.school.SchoolDTO;
import com.egradebook.eGradeBook.DTOs.teacher.TeacherDTO;
import com.egradebook.eGradeBook.DTOs.user.UserDTO;
import com.egradebook.eGradeBook.entities.Grade;
import com.egradebook.eGradeBook.entities.Qualification;
import com.egradebook.eGradeBook.entities.Teacher;
import com.egradebook.eGradeBook.entities.User;
import com.egradebook.eGradeBook.exceptions.QualificationNotFoundException;
import com.egradebook.eGradeBook.exceptions.SchoolNotFoundException;
import com.egradebook.eGradeBook.exceptions.TeacherNotFoundException;
import com.egradebook.eGradeBook.exceptions.UserNotFoundException;
import com.egradebook.eGradeBook.repositories.QualificationsRepository;
import com.egradebook.eGradeBook.repositories.TeacherRepository;
import com.egradebook.eGradeBook.repositories.UserRepository;
import com.egradebook.eGradeBook.services.GradeService;
import com.egradebook.eGradeBook.services.QualificationService;
import com.egradebook.eGradeBook.services.SchoolService;
import com.egradebook.eGradeBook.services.StudentService;
import com.egradebook.eGradeBook.services.TeacherService;
import com.egradebook.eGradeBook.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    private final TeacherRepository teacherRepository;
    private final SchoolService schoolService;
    private final UserService userService;
    private final QualificationService qualificationService;
    private final QualificationsRepository qualificationsRepository;
    private final StudentService studentService;
    private final UserRepository userRepository;
    private final GradeService gradeService;

    @GetMapping
    public String showTeacher(Model model) {
        List<TeacherDTO> teacherDTOList = teacherService.getAllTeachersDto();
        model.addAttribute("teacherDTOList", teacherDTOList);
        return "teacher/list-teacher";
    }

    @GetMapping("/create")
    public String showCreateTeacherForm(Model model) {

        List<UserDTO> userDTOList = userService.getAllUsersDto();
        List<SchoolDTO> schoolDTOList = schoolService.getAllSchoolsDto();
        List<Qualification> qualificationList = qualificationService.getQualifications();

        model.addAttribute("schoolDTOList", schoolDTOList);
        model.addAttribute("userDTOList", userDTOList);
        model.addAttribute("qualificationList", qualificationList);

        return "teacher/create-teacher";
    }

    @PostMapping("/create")
    public String createTeacher(@RequestParam Long userId,
                                @RequestParam Long schoolId,
                                @RequestParam List<Long> qualificationIds) {

        teacherService.createTeacher(userId, schoolId, qualificationIds);
        return "redirect:/teacher";
    }
    

    // TODO Fix cascade deletion
    @GetMapping("/delete/{teacherId}")
    public String deleteTeacher(@PathVariable Long teacherId) {

        try {
            teacherService.deleteTeacher(teacherId);
        } catch (TeacherNotFoundException e) {
            // TODO Handle Properly, Create Error Page
            throw new RuntimeException(e);
        }

        return "redirect:/teacher";
    }

    @GetMapping("/edit")
    public String showEditTeacherForm(Model model) {
        model.addAttribute("teachers", teacherRepository.findAll());
        model.addAttribute("qualifications", qualificationsRepository.findAll());
        return "teacher/edit-teacher";
    }

    @PostMapping("/save")
    public String saveTeacher(@RequestParam Long teacherId, @RequestParam List<Long> qualificationIds) {
        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);

        if (teacher != null) {
            Set<Qualification> qualifications = qualificationIds.stream()
                    .map(id -> qualificationsRepository.findById(id).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());

            teacher.setQualificationSet(qualifications);
            teacherRepository.save(teacher);
        }

        return "redirect:/teacher";
    }

    @GetMapping("/{teacherId}/students")
    public String showTeacherStudents(@PathVariable Long teacherId,
                                      Model model) {

        model.addAttribute("studentList", studentService.findStudentsByTeacherId(teacherId));

        return "teacher/teacher-view";
    }

    @GetMapping("/edit/{gradeId}")
    public String showEditStudentForm(@PathVariable Long gradeId, Model model) {
        model.addAttribute("grade", gradeService.findByStudentId(gradeId));
        return "teacher/edit-student-grade";
    }




}


    //
//    private final TeacherRepository teacherRepository;
//    private final UserRepository userRepository;
//    private final QualificationsRepository qualificationsRepository;
//
//    public TeacherController(TeacherRepository teacherRepository, UserRepository userRepository, QualificationsRepository qualificationsRepository) {
//        this.teacherRepository = teacherRepository;
//        this.userRepository = userRepository;
//        this.qualificationsRepository = qualificationsRepository;
//    }
//
//    @GetMapping
//    public String showTeacher(Model model) {
//        List<TeacherDTO> teacherDTOList = teacherRepository.getTeachersDTO();
//        model.addAttribute("teacherDTOList", teacherDTOList);
//        return "teacher/list-teacher";
//    }
//
//    @GetMapping("/create")
//    public String showCreateTeacherForm(Model model) {
//        List<UserDTO> userDTOList = userRepository.findAllUserDTO();
//        model.addAttribute("userDTOList", userDTOList);
//        return "teacher/create-teacher";
//    }
//
//    @PostMapping("/create")
//    public String createTeacher(@RequestParam Long userId) {
//
//        User user = userRepository.findById(userId).orElse(null);
//
//        if (user != null) {
//            Teacher teacher = Teacher.builder()
//                    .id(user.getId())
//                    .firstName(user.getFirstName())
//                    .lastName(user.getLastName())
//                    .address(user.getAddress())
//                    .email(user.getEmail())
//                    .passwordHash(user.getPasswordHash())
//                    .phoneNumber(user.getPhoneNumber())
//                    .enabled(user.getEnabled())
//                    .build();
//
//            teacherRepository.save(teacher);
//        }
//
//        return "redirect:/teacher";
//    }
//
//    @GetMapping("/delete/{teacherId}")
//    public String deleteTeacher(@PathVariable Long teacherId) {
//        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
//
//        if (teacher != null) {
//            teacherRepository.deleteById(teacherId);
//        }
//
//        return "redirect:/teacher";
//    }
//
//    @GetMapping("/edit")
//    public String showEditTeacherForm(Model model) {
//        model.addAttribute("teachers", teacherRepository.findAll());
//        model.addAttribute("qualifications", qualificationsRepository.findAll());
//        return "teacher/edit-teacher";
//    }
//
//    @PostMapping("/save")
//    public String saveTeacher(@RequestParam Long teacherId, @RequestParam List<Long> qualificationIds) {
//        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
//
//        if (teacher != null) {
//            Set<Qualification> qualifications = qualificationIds.stream()
//                    .map(id -> qualificationsRepository.findById(id).orElse(null))
//                    .filter(Objects::nonNull)
//                    .collect(Collectors.toSet());
//
//            teacher.setQualificationSet(qualifications);
//            teacherRepository.save(teacher);
//        }
//
//        return "redirect:/teacher";
//    }
