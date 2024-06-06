package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.entities.ClassLetterCatalog;
import com.egradebook.eGradeBook.repositories.ClassLetterCatalogRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/letter-catalog")
public class ClassLetterCatalogController {

    private final ClassLetterCatalogRepository classLetterCatalogRepository;

    public ClassLetterCatalogController(ClassLetterCatalogRepository classLetterCatalogRepository) {
        this.classLetterCatalogRepository = classLetterCatalogRepository;
    }

    @GetMapping
    public String showLetterCatalog(Model model) {
        List<ClassLetterCatalog> classLetterCatalogList = classLetterCatalogRepository.findAll();
        model.addAttribute("classLetterCatalogList", classLetterCatalogList);

        return "class-letter-catalog/list-letter-catalog";
    }

    @GetMapping("/create")
    public String showCreateLetterCatalogForm(Model model) {
        return "class-letter-catalog/create-letter";
    }

    @PostMapping("/create")
    public String createLetterCatalog(@RequestParam String letterSign) {

        ClassLetterCatalog classLetterCatalog = new ClassLetterCatalog();
        classLetterCatalog.setCLetter(letterSign);
        classLetterCatalogRepository.save(classLetterCatalog);

        return "redirect:/letter-catalog";
    }

    @GetMapping("/edit/{gradeId}")
    public String showEditLetterCatalogForm(@PathVariable Long gradeId, Model model) {

        ClassLetterCatalog letterCatalog =
                classLetterCatalogRepository.findById(gradeId).orElse(null);

        model.addAttribute("letter", letterCatalog);

        return "class-letter-catalog/edit-letter";
    }

    @PostMapping("/update")
    public String updateLetterCatalog(@RequestParam Long letterId, @RequestParam String letterSign) {

        ClassLetterCatalog letterCatalog =
                classLetterCatalogRepository.findById(letterId).orElse(null);

        if (letterCatalog != null) {
            letterCatalog.setCLetter(letterSign);
            classLetterCatalogRepository.save(letterCatalog);
        }

        return "redirect:/letter-catalog";
    }

    @GetMapping("/delete/{letterId}")
    public String deleteLetterCatalog(@PathVariable Long letterId) {
        ClassLetterCatalog letterCatalog =
                classLetterCatalogRepository.findById(letterId).orElse(null);

        if (letterCatalog != null) {
            classLetterCatalogRepository.delete(letterCatalog);
        }

        return "redirect:/letter-catalog";
    }
}
