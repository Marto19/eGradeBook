package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.DTOs.parent.ParentDTO;
import com.egradebook.eGradeBook.DTOs.user.UserDTO;
import com.egradebook.eGradeBook.entities.Parent;
import com.egradebook.eGradeBook.entities.User;
import com.egradebook.eGradeBook.repositories.ParentRepository;
import com.egradebook.eGradeBook.repositories.UserRepository;
import com.egradebook.eGradeBook.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/parent")
public class ParentController {

    private final ParentRepository parentRepository;
    private final UserRepository userRepository;

    private final StudentService studentService;

    public ParentController(ParentRepository parentRepository, UserRepository userRepository, StudentService studentService) {
        this.parentRepository = parentRepository;
        this.userRepository = userRepository;
        this.studentService = studentService;
    }

    @GetMapping
    public String showParent(Model model) {
        List<ParentDTO> parentDTOList = parentRepository.getParentsDTO();
        model.addAttribute("parentDTOList", parentDTOList);
        return "parent/list-parent";
    }

    @GetMapping("/create")
    public String showCreateParentForm(Model model) {
        List<Long> parentUserIds = parentRepository.getParentUserIds();
        List<UserDTO> userDTOList = userRepository.findAllUserDTO().stream()
                .filter(userDTO -> !parentUserIds.contains(userDTO.getId()))
                .collect(Collectors.toList());
        model.addAttribute("userDTOList", userDTOList);
        return "parent/create-parent";
    }

    @PostMapping("/create")
    public String createParent(@RequestParam Long userId, Model model) {

        Optional<User> optionalUser = userRepository.findById(userId);

        if (!optionalUser.isPresent()) {
            model.addAttribute("errorMessage", "User with id " + userId + " does not exist");
            return "error";
        }

        User user = optionalUser.get();
        if (user.getFirstName() == null || user.getLastName() == null || user.getEmail() == null || user.getAddress() == null) {
            model.addAttribute("errorMessage", "User with id " + userId + " does not have all required fields populated");
            return "error";
        }

        Parent parent = Parent.builder()
                .user(user)
                .build();

        parentRepository.save(parent);

        return "redirect:/parent";
    }

    @GetMapping("/delete/{parentId}")
    public String deleteParent(@PathVariable Long parentId) {
        Parent parent = parentRepository.findById(parentId).orElse(null);

        if (parent != null) {
            parentRepository.deleteById(parentId);
        }

        return "redirect:/parent";
    }

    @GetMapping("/{parentId}/students")
    public String showParentStudents(@PathVariable Long parentId,
                                      Model model) {

        model.addAttribute("studentList", studentService.findStudentsByTeacherId(parentId));

        return "teacher/teacher-view";
    }
}
