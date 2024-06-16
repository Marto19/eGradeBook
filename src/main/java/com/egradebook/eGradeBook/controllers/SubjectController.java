package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.exceptions.QualificationNotFoundException;
import com.egradebook.eGradeBook.exceptions.SubjectNotFoundException;
import com.egradebook.eGradeBook.services.QualificationService;
import com.egradebook.eGradeBook.services.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectService subjectService;
    private final QualificationService qualificationService;

    @GetMapping
    public String getAllSubjects(Model model) {
        model.addAttribute("subjectList", subjectService.listAllSubjects());
        return "subject/list-subject";
    }

    @GetMapping("/create")
    public String showCreateSubjectForm(Model model) {
        model.addAttribute("qualificationList", qualificationService.getQualifications());
        return "/subject/create-subject";
    }

    @PostMapping("/create")
    public String createSubject(@RequestParam String subjectName,
                                @RequestParam Long qualificationId,
                                @RequestParam String subjectDescription) throws QualificationNotFoundException {

        subjectService.createSubject(subjectName, qualificationId, subjectDescription);
        return "redirect:/subject";
    }

    @GetMapping("/edit/{subjectId}")
    public String showEditSubjectForm(@PathVariable Long subjectId,
                                      Model model) throws SubjectNotFoundException {

        model.addAttribute("subject", subjectService.getSubjectById(subjectId));
        model.addAttribute("qualificationList", qualificationService.getQualifications());
        return "/subject/edit-subject";
    }

    @PostMapping("/update")
    public String updateSubject(@RequestParam Long subjectId,
                                @RequestParam String subjectName,
                                @RequestParam Long qualificationId,
                                @RequestParam String subjectDescription) throws SubjectNotFoundException, QualificationNotFoundException {

        subjectService.updateSubject(subjectId, subjectName, qualificationId, subjectDescription);

        return "redirect:/subject";
    }

    @GetMapping("/delete/{subjectId}")
    public String deleteSubject(@PathVariable Long subjectId) throws SubjectNotFoundException {
        subjectService.deleteSubject(subjectId);
        return "redirect:/subject";
    }

}
