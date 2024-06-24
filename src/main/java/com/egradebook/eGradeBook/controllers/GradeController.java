package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.DTOs.GradeDTO;
import com.egradebook.eGradeBook.entities.Grade;
import com.egradebook.eGradeBook.services.GradeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/grades")
@AllArgsConstructor
public class GradeController {
    private final GradeService gradeService;

    @GetMapping
    public String listGrades(Model model) {
        List<Grade> grades = gradeService.getAllGradesWithDetails();
        model.addAttribute("grades", grades);
        return "grade/list-grades";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("grade", new GradeDTO());
        model.addAttribute("gradeCatalogs", gradeService.getAllGradeCatalogs());
        model.addAttribute("subjects", gradeService.getAllSubjects());
        model.addAttribute("students", gradeService.getAllStudents());
        model.addAttribute("teachers", gradeService.getAllTeachers());
        return "grade/create-grade";
    }

    @PostMapping
    public String createGrade(@ModelAttribute("grade") @Valid GradeDTO gradeDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("gradeCatalogs", gradeService.getAllGradeCatalogs());
            model.addAttribute("subjects", gradeService.getAllSubjects());
            model.addAttribute("students", gradeService.getAllStudents());
            model.addAttribute("teachers", gradeService.getAllTeachers());
            return "grade/create-grade";
        }
        gradeService.createGrade(gradeDTO);
        return "redirect:/grades";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        GradeDTO gradeDTO = gradeService.getGradeById(id);
        model.addAttribute("grade", gradeDTO);
        model.addAttribute("gradeCatalogs", gradeService.getAllGradeCatalogs());
        model.addAttribute("subjects", gradeService.getAllSubjects());
        model.addAttribute("students", gradeService.getAllStudents());
        model.addAttribute("teachers", gradeService.getAllTeachers());
        return "grade/edit-grade";
    }

    @PostMapping("/edit/{id}")
    public String updateGrade(@PathVariable("id") Long id, @ModelAttribute("grade") @Valid GradeDTO gradeDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("gradeCatalogs", gradeService.getAllGradeCatalogs());
            model.addAttribute("subjects", gradeService.getAllSubjects());
            model.addAttribute("students", gradeService.getAllStudents());
            model.addAttribute("teachers", gradeService.getAllTeachers());
            return "grade/edit-grade";
        }
        gradeService.updateGrade(id, gradeDTO);
        return "redirect:/grades";
    }

    @GetMapping("/delete/{id}")
    public String deleteGrade(@PathVariable("id") Long id) {
        gradeService.deleteGrade(id);
        return "redirect:/grades";
    }
}
