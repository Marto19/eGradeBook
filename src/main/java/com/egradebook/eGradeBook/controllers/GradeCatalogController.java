package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.entities.GradeCatalog;
import com.egradebook.eGradeBook.entities.School;
import com.egradebook.eGradeBook.repositories.GradeCatalogRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/grade-catalog")
public class GradeCatalogController {

    private final GradeCatalogRepository gradeCatalogRepository;

    public GradeCatalogController(GradeCatalogRepository gradeCatalogRepository) {
        this.gradeCatalogRepository = gradeCatalogRepository;
    }

    @GetMapping
    public String showGradeCatalog(Model model) {
        List<GradeCatalog> gradeCatalogs = gradeCatalogRepository.findAll();
        model.addAttribute("gradeCatalogs", gradeCatalogs);
        return "grade-catalog/list-catalog";
    }

    @GetMapping("/create")
    public String showCreateGradeCatalogForm(Model model) {
        return "grade-catalog/create-catalog";
    }

    @PostMapping("/create")
    public String createGradeCatalog(@RequestParam Double gradeSign) {
        GradeCatalog gradeCatalog = new GradeCatalog();
        gradeCatalog.setGradeNumber(gradeSign);
        gradeCatalogRepository.save(gradeCatalog);
        return "redirect:/grade-catalog";
    }

    @GetMapping("/edit/{gradeId}")
    public String showEditGradeCatalogForm(@PathVariable Long gradeId, Model model) {
        GradeCatalog gradeCatalog = gradeCatalogRepository.findById(gradeId).orElse(null);
        model.addAttribute("grade", gradeCatalog);
        return "grade-catalog/edit-catalog";
    }

    @PostMapping("/update")
    public String updateGradeCatalog(@RequestParam Long gradeId, @RequestParam Double gradeSign) {
        GradeCatalog gradeCatalog = gradeCatalogRepository.findById(gradeId).orElse(null);
        if (gradeCatalog != null) {
            gradeCatalog.setGradeNumber(gradeSign);
            gradeCatalogRepository.save(gradeCatalog);
        }
        return "redirect:/grade-catalog";
    }

    @GetMapping("/delete/{gradeId}")
    public String deleteGradeCatalog(@PathVariable Long gradeId) {
        GradeCatalog gradeCatalog = gradeCatalogRepository.findById(gradeId).orElse(null);

        if (gradeCatalog != null) {
            gradeCatalogRepository.delete(gradeCatalog);
        }

        return "redirect:/grade-catalog";
    }
}
