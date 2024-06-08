package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.DTOs.teacher.TeacherDTO;
import com.egradebook.eGradeBook.DTOs.user.UserDTO;
import com.egradebook.eGradeBook.entities.Principal;
import com.egradebook.eGradeBook.entities.Qualification;
import com.egradebook.eGradeBook.entities.Teacher;
import com.egradebook.eGradeBook.entities.User;
import com.egradebook.eGradeBook.repositories.QualificationsRepository;
import com.egradebook.eGradeBook.repositories.TeacherRepository;
import com.egradebook.eGradeBook.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final QualificationsRepository qualificationsRepository;

    public TeacherController(TeacherRepository teacherRepository, UserRepository userRepository, QualificationsRepository qualificationsRepository) {
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;
        this.qualificationsRepository = qualificationsRepository;
    }

    @GetMapping
    public String showTeacher(Model model) {
        List<TeacherDTO> teacherDTOList = teacherRepository.getTeachersDTO();
        model.addAttribute("teacherDTOList", teacherDTOList);
        return "teacher/list-teacher";
    }

    @GetMapping("/create")
    public String showCreateTeacherForm(Model model) {
        List<UserDTO> userDTOList = userRepository.findAllUserDTO();
        model.addAttribute("userDTOList", userDTOList);
        return "teacher/create-teacher";
    }

    @PostMapping("/create")
    public String createTeacher(@RequestParam Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            Teacher teacher = Teacher.builder()
                    .id(user.getId())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .address(user.getAddress())
                    .email(user.getEmail())
                    .passwordHash(user.getPasswordHash())
                    .phoneNumber(user.getPhoneNumber())
                    .enabled(user.getEnabled())
                    .build();

            teacherRepository.save(teacher);
        }

        return "redirect:/teacher";
    }

    @GetMapping("/delete/{teacherId}")
    public String deleteTeacher(@PathVariable Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);

        if (teacher != null) {
            teacherRepository.deleteById(teacherId);
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
    public String saveTeacher(@RequestParam Long teacherId, @RequestParam Long qualificationId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
        Qualification qualification = qualificationsRepository.findById(qualificationId).orElse(null);

        if (teacher != null && qualification != null) {
            teacher.getQualificationSet().add(qualification);
            teacherRepository.save(teacher);
        }

        return "redirect:/teacher";
    }

}
