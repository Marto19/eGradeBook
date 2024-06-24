package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.DTOs.classes.SchoolClassDTO;
import com.egradebook.eGradeBook.DTOs.classes.SchoolClassesNoId;
import com.egradebook.eGradeBook.DTOs.parent.ParentDTO;
import com.egradebook.eGradeBook.DTOs.school.SchoolDTO;
import com.egradebook.eGradeBook.DTOs.student.StudentDTO;
import com.egradebook.eGradeBook.DTOs.teacher.TeacherDTO;
import com.egradebook.eGradeBook.DTOs.user.UserDTO;
import com.egradebook.eGradeBook.entities.*;
import com.egradebook.eGradeBook.entities.Class;
import com.egradebook.eGradeBook.exceptions.EntityAlreadyExistsException;
import com.egradebook.eGradeBook.exceptions.StudentNotFoundException;
import com.egradebook.eGradeBook.repositories.UserRepository;
import com.egradebook.eGradeBook.services.GradeService;
import com.egradebook.eGradeBook.services.StudentService;
import com.egradebook.eGradeBook.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.ListResourceBundle;

@AllArgsConstructor

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final GradeService gradeService;

    @GetMapping
    public String showStudentList(Model model) {
        List<StudentDTO> studentDTOList = studentService.getAllStudentsDto();
        model.addAttribute("studentDTOList", studentDTOList);
        return "student/list-students";
    }

    @GetMapping("/create")
    public String showCreateStudentForm(Model model) {
        List<UserDTO> userDTOList = userService.getAllUsersDto();
        List<ParentDTO> parentDTOList = userService.getAllParentsDto();
        List<SchoolDTO> schoolDTOList = userService.getAllSchoolsDto();
        List<SchoolClassDTO> classList = studentService.getAllClassesDto();

        model.addAttribute("userDTOList", userDTOList);
        model.addAttribute("parentDTOList", parentDTOList);
        model.addAttribute("schoolDTOList", schoolDTOList);
        model.addAttribute("classList", classList);

        return "student/create-student";
    }

    @PostMapping("/create")
    public String createStudent(@RequestParam Long userId,
                                @RequestParam Long parentId,
                                @RequestParam Long classId,
                                @RequestParam Long schoolId) throws RoleNotFoundException {

        studentService.createStudent(userId, classId, parentId, schoolId);

        return "redirect:/student";
    }


    @GetMapping("/delete/{studentId}")
    public String deleteStudent(@PathVariable Long studentId) {
        try {
            studentService.deleteStudent(studentId);
            return "redirect:/student";
        } catch (StudentNotFoundException e) {
            // Handle exception if student not found
            return "error"; // Redirect to error page or handle as required
        }
    }

    @GetMapping("/grades/{studentId}")
    public String showStudentGrades(@PathVariable Long studentId, Model model) {

        model.addAttribute("gradesList", gradeService.findByStudentId(studentId));

        return "student/student-grades";
    }


}
