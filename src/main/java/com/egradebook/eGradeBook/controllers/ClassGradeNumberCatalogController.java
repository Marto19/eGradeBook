package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.entities.ClassGradeNumberCatalog;
import com.egradebook.eGradeBook.exceptions.ClassGradeNumberCatalogNotFoundException;
import com.egradebook.eGradeBook.repositories.ClassGradeNumberCatalogRepository;
import com.egradebook.eGradeBook.services.ClassGradeNumberCatalogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor

@Controller
@RequestMapping("/class-grade-number-catalog")
public class ClassGradeNumberCatalogController {

//    private final ClassGradeNumberCatalogRepository classGradeNumberCatalogRepository;
    private final ClassGradeNumberCatalogService classGradeNumberCatalogService;

    @GetMapping
    public String showClassGradeNumberCatalog(Model model) {
        List<ClassGradeNumberCatalog> classGradeNumberCatalogs = classGradeNumberCatalogService.getAllClassGradeNumberCatalog();
        model.addAttribute("classGradeNumberCatalogs", classGradeNumberCatalogs);
        return "class-grade-number-catalog/list-catalog";
    }

    @GetMapping("/create")
    public String showCreateClassGradeNumberCatalogForm(Model model) {
        return "class-grade-number-catalog/create-catalog";
    }

    @PostMapping("/create")
    public String createClassGradeNumberCatalog(@RequestParam int cgNumber) {
        classGradeNumberCatalogService.create(cgNumber);
        return "redirect:/class-grade-number-catalog";
    }

    @GetMapping("/edit/{cgId}")
    public String showEditClassGradeNumberCatalogForm(@PathVariable Long cgId, Model model) {
        ClassGradeNumberCatalog classGradeNumberCatalog = null;

        try {
            classGradeNumberCatalog = classGradeNumberCatalogService.getClassGradeNumberById(cgId);
        } catch (ClassGradeNumberCatalogNotFoundException e) {
            // TODO Handle properly
            throw new RuntimeException(e);
        }

        model.addAttribute("classGradeNumberCatalog", classGradeNumberCatalog);
        return "class-grade-number-catalog/edit-catalog";
    }

    @PostMapping("/update")
    public String updateClassGradeNumberCatalog(@RequestParam int cgId, @RequestParam int cgNumber) {

        try {
            classGradeNumberCatalogService.update(cgId, cgNumber);
        } catch (ClassGradeNumberCatalogNotFoundException e) {
            // TODO Handle properly
            throw new RuntimeException(e);
        }

        return "redirect:/class-grade-number-catalog";
    }

    @GetMapping("/delete/{cgId}")
    public String deleteClassGradeNumberCatalog(@PathVariable int cgId) {

        try {
            classGradeNumberCatalogService.delete(cgId);
        } catch (ClassGradeNumberCatalogNotFoundException e) {
            // TODO Handle properly
            throw new RuntimeException(e);
        }
        
        return "redirect:/class-grade-number-catalog";
    }
}
