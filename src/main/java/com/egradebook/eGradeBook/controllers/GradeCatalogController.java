package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.entities.GradeCatalog;
import com.egradebook.eGradeBook.exceptions.GradeCatalogNotFoundException;
import com.egradebook.eGradeBook.services.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@AllArgsConstructor

@Controller
@RequestMapping("/grade-catalog")
public class GradeCatalogController {

    private final GradeService gradeService;

    @GetMapping
    public String showGradeCatalog(Model model) {
        List<GradeCatalog> gradeCatalogs = gradeService.getAll();
        model.addAttribute("gradeCatalogs", gradeCatalogs);
        return "grade-catalog/list-catalog";
    }

    @GetMapping("/create")
    public String showCreateGradeCatalogForm(Model model) {
        return "grade-catalog/create-catalog";
    }

    @PostMapping("/create")
    public String createGradeCatalog(@RequestParam Double gradeSign) {

        gradeService.save(gradeSign);

        return "redirect:/grade-catalog";
    }

    @GetMapping("/edit/{gradeId}")
    public String showEditGradeCatalogForm(@PathVariable Long gradeId, Model model) {

        GradeCatalog gradeCatalog;

        try {
            gradeCatalog = gradeService.getById(gradeId);
        } catch (GradeCatalogNotFoundException e) {
            // TODO Handle properly
            throw new RuntimeException(e);
        }

        model.addAttribute("grade", gradeCatalog);
        return "grade-catalog/edit-catalog";
    }

    @PostMapping("/update")
    public String updateGradeCatalog(@RequestParam Long gradeId, @RequestParam Double gradeSign) {

        try {
            gradeService.update(gradeId, gradeSign);
        } catch (GradeCatalogNotFoundException e) {
            // TODO Handle properly
            throw new RuntimeException(e);
        }

        return "redirect:/grade-catalog";
    }

    @GetMapping("/delete/{gradeId}")
    public String deleteGradeCatalog(@PathVariable Long gradeId) {

        try {
            gradeService.delete(gradeId);
        } catch (GradeCatalogNotFoundException e) {
            // TODO Handle properly
            throw new RuntimeException(e);
        }

        return "redirect:/grade-catalog";
    }
}
