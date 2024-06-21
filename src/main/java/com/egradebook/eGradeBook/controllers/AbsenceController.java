package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.services.AbsenceService;
import com.egradebook.eGradeBook.services.SubjectService;
import com.egradebook.eGradeBook.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/absences")
@AllArgsConstructor
public class AbsenceController
{
    private final AbsenceService absenceService;

    private final SubjectService subjectService;

    private final UserService userService;

    @GetMapping
    public String showAbsencesPage(Model model)
    {
        model.addAttribute("absences", absenceService.getAllAbsenceDTOs());

        return "absence/absences";
    }


}
