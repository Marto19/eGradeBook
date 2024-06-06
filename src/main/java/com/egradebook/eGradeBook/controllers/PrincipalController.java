package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.DTOs.principle.PrincipalDTO;
import com.egradebook.eGradeBook.DTOs.principle.PrincipalSchoolDTO;
import com.egradebook.eGradeBook.DTOs.school.SchoolDTO;
import com.egradebook.eGradeBook.entities.Principal;
import com.egradebook.eGradeBook.entities.School;
import com.egradebook.eGradeBook.repositories.PrincipalRepository;
import com.egradebook.eGradeBook.repositories.SchoolRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/principal")
public class PrincipalController {

    // TODO If a user is already assigned to a school (as a principal)
    // TODO do not show them on the drop down list
    // TODO Implement the search functionality

    private final PrincipalRepository principalRepository;
    private final SchoolRepository schoolRepository;

    public PrincipalController(PrincipalRepository principleRepository, SchoolRepository schoolRepository) {
        this.principalRepository = principleRepository;
        this.schoolRepository = schoolRepository;
    }


    @GetMapping
    public String getAllPrinciples(Model model) {
        List<PrincipalSchoolDTO> principleDTOList = principalRepository.getPrincipalDTOsWithSchool();
        model.addAttribute("principleDTOList", principleDTOList);
        return "principal/list-principal";
    }

    @GetMapping("/create")
    public String createPrincipal(Model model) {

        List<SchoolDTO> schoolDTOList = schoolRepository.getAllSchools();
        List<PrincipalDTO> principalDTOList = principalRepository.getPrincipalDTOs();

        model.addAttribute("schoolDTOList", schoolDTOList);
        model.addAttribute("principalDTOList", principalDTOList);

        return "principal/create-principal";
    }

    @PostMapping("/save")
    public String savePrincipal(@RequestParam Long schoolId, @RequestParam Long principalId) {

        School school = schoolRepository.findById(schoolId).orElse(null);
        Principal principal = principalRepository.findById(principalId).orElse(null);

        if (school != null && principal != null) {
            school.setPrincipal(principal);
            schoolRepository.save(school);
        }

        return "redirect:/principal";
    }

    @GetMapping("/delete/{schoolId}")
    public String deletePrincipal(@PathVariable Long schoolId) {
        School school = schoolRepository.findById(schoolId).orElse(null);
        if (school != null) {
            school.setPrincipal(null);
            schoolRepository.save(school);
        }
        return "redirect:/principal";
    }

    @GetMapping("/edit/{schoolId}")
    public String showEditForm(@PathVariable Long schoolId, Model model) {
        School school = schoolRepository.findById(schoolId).orElse(null);
        List<PrincipalDTO> principalDTOList = principalRepository.getPrincipalDTOs();

        model.addAttribute("principalDTOList", principalDTOList);
        model.addAttribute("school", school);

        return "principal/edit-principal";
    }

    @PostMapping("/edit")
    public String updatePrincipal(@RequestParam Long id, @RequestParam Long principalId) {
        School school = schoolRepository.findById(id).orElse(null);
        if (school != null) {
            Principal principal = principalRepository.findById(principalId).orElse(null);
            if (principal != null) {
                school.setPrincipal(principal);
                schoolRepository.save(school);
            }
        }
        return "redirect:/principal";
    }


}
