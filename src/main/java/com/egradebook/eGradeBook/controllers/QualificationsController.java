package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.entities.Qualification;
import com.egradebook.eGradeBook.repositories.QualificationsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/qualifications")
public class QualificationsController {

    private final QualificationsRepository qualificationRepository;

    public QualificationsController(QualificationsRepository qualificationRepository) {
        this.qualificationRepository = qualificationRepository;
    }

    @GetMapping
    public String showQualifications(Model model) {
        List<Qualification> qualifications = qualificationRepository.findAll();
        model.addAttribute("qualifications", qualifications);
        return "qualifications/list-qualifications";
    }

    @GetMapping("/create")
    public String showCreateQualificationForm(Model model) {
        return "/qualifications/create-qualifications";
    }

    @PostMapping("/create")
    public String createQualification(@RequestParam String signature, @RequestParam String description) {
        Qualification qualification = new Qualification();
        qualification.setSignature(signature);
        qualification.setDescription(description);
        qualificationRepository.save(qualification);
        return "redirect:/qualifications";
    }

    @GetMapping("/edit/{id}")
    public String showEditQualificationForm(@PathVariable Long id, Model model) {
        Qualification qualification = qualificationRepository.findById(id).orElse(null);
        model.addAttribute("qualification", qualification);
        return "qualifications/edit-qualifications";
    }

    @PostMapping("/update")
    public String updateQualification(@RequestParam Long id, @RequestParam String signature, @RequestParam String description) {
        Qualification qualification = qualificationRepository.findById(id).orElse(null);
        if (qualification != null) {
            qualification.setSignature(signature);
            qualification.setDescription(description);
            qualificationRepository.save(qualification);
        }
        return "redirect:/qualifications";
    }

    @GetMapping("/delete/{id}")
    public String deleteQualification(@PathVariable Long id) {
        Qualification qualification = qualificationRepository.findById(id).orElse(null);

        if (qualification != null) {
            qualificationRepository.delete(qualification);
        } else {
            // TODO Handle null
        }

        return "redirect:/qualifications";
    }
}
