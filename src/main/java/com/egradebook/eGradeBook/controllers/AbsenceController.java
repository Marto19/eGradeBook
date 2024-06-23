package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.DTOs.absence.AbsenceDTO;
import com.egradebook.eGradeBook.exceptions.StudentNotFoundException;
import com.egradebook.eGradeBook.services.AbsenceService;
import com.egradebook.eGradeBook.services.StudentService;
import com.egradebook.eGradeBook.services.SubjectService;
import com.egradebook.eGradeBook.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/absences")
@AllArgsConstructor
public class AbsenceController
{
    private final AbsenceService absenceService;

    private final SubjectService subjectService;

    private final UserService userService;

    private final StudentService studentService;

    @GetMapping
    public String showAbsencesPage(Model model)
    {
        model.addAttribute("absences", absenceService.getAllAbsenceDTOs());

        return "absence/absences";
    }

    @GetMapping("/create")
    public String createAbsence(Model model) {
        model.addAttribute("absence", new AbsenceDTO());
        model.addAttribute("students", userService.getAllStudentsDto());
        model.addAttribute("subjects", subjectService.getAllSubjectsDto());
        return "absence/create-absence";
    }

    @PostMapping("/create")
    public String createAbsence(@ModelAttribute("absence") AbsenceDTO absenceDTO, BindingResult result) throws StudentNotFoundException {
        if (result.hasErrors()) {
            return "absence/create-absence";
        }
        absenceService.createAbsence(absenceDTO);
        return "redirect:/absences";
    }


}
