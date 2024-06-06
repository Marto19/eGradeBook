package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.entities.ClassGradeNumberCatalog;
import com.egradebook.eGradeBook.repositories.ClassGradeNumberCatalogRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/class-grade-number-catalog")
public class ClassGradeNumberCatalogController {

    private final ClassGradeNumberCatalogRepository classGradeNumberCatalogRepository;

    public ClassGradeNumberCatalogController(ClassGradeNumberCatalogRepository classGradeNumberCatalogRepository) {
        this.classGradeNumberCatalogRepository = classGradeNumberCatalogRepository;
    }

    @GetMapping
    public String showClassGradeNumberCatalog(Model model) {
        List<ClassGradeNumberCatalog> classGradeNumberCatalogs = classGradeNumberCatalogRepository.findAll();
        model.addAttribute("classGradeNumberCatalogs", classGradeNumberCatalogs);
        return "class-grade-number-catalog/list-catalog";
    }

    @GetMapping("/create")
    public String showCreateClassGradeNumberCatalogForm(Model model) {
        return "class-grade-number-catalog/create-catalog";
    }

    @PostMapping("/create")
    public String createClassGradeNumberCatalog(@RequestParam int cgNumber) {
        ClassGradeNumberCatalog classGradeNumberCatalog = new ClassGradeNumberCatalog();
        classGradeNumberCatalog.setCgNumber(cgNumber);
        classGradeNumberCatalogRepository.save(classGradeNumberCatalog);
        return "redirect:/class-grade-number-catalog";
    }

    @GetMapping("/edit/{cgId}")
    public String showEditClassGradeNumberCatalogForm(@PathVariable Long cgId, Model model) {
        ClassGradeNumberCatalog classGradeNumberCatalog = classGradeNumberCatalogRepository.findById(cgId).orElse(null);
        model.addAttribute("classGradeNumberCatalog", classGradeNumberCatalog);
        return "class-grade-number-catalog/edit-catalog";
    }

    @PostMapping("/update")
    public String updateClassGradeNumberCatalog(@RequestParam Long cgId, @RequestParam int cgNumber) {
        ClassGradeNumberCatalog classGradeNumberCatalog = classGradeNumberCatalogRepository.findById(cgId).orElse(null);
        if (classGradeNumberCatalog != null) {
            classGradeNumberCatalog.setCgNumber(cgNumber);
            classGradeNumberCatalogRepository.save(classGradeNumberCatalog);
        }
        return "redirect:/class-grade-number-catalog";
    }

    @GetMapping("/delete/{cgId}")
    public String deleteClassGradeNumberCatalog(@PathVariable Long cgId) {
        ClassGradeNumberCatalog classGradeNumberCatalog = classGradeNumberCatalogRepository.findById(cgId).orElse(null);

        if (classGradeNumberCatalog != null) {
            classGradeNumberCatalogRepository.delete(classGradeNumberCatalog);
        } else {
            // TODO Handle null
        }

        return "redirect:/class-grade-number-catalog";
    }
}
