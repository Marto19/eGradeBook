package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.DTOs.school.SchoolDTO;
import com.egradebook.eGradeBook.entities.Principal;
import com.egradebook.eGradeBook.entities.School;
import com.egradebook.eGradeBook.repositories.PrincipalRepository;
import com.egradebook.eGradeBook.repositories.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/school")
public class SchoolController {

    private final SchoolRepository schoolRepository;
    private final PrincipalRepository principalRepository;

    @Autowired
    public SchoolController(SchoolRepository schoolRepository, PrincipalRepository principalRepository) {
        this.schoolRepository = schoolRepository;
        this.principalRepository = principalRepository;
    }

    @GetMapping
    public String getAllSchools(Model model) {
        List<SchoolDTO> schoolDTOList = schoolRepository.getAllSchools();
        model.addAttribute("schoolDTOList", schoolDTOList);
        return "school/list-school";
    }

    @GetMapping("/create")
    public String createSchool(Model model) {
        model.addAttribute("schoolDTO", new SchoolDTO());
        return "school/create-school";
    }

    @PostMapping("/save")
    public String saveSchool(@ModelAttribute SchoolDTO schoolDTO) {
        School school = new School();
        school.setName(schoolDTO.getName());
        school.setAddress(schoolDTO.getAddress());
        schoolRepository.save(school);
        return "redirect:/school/get-schools";
    }

    @GetMapping("/delete/{id}")
    public String deleteSchool(@PathVariable Long id) {
        schoolRepository.deleteById(id);
        return "redirect:/school/get-schools";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        School school = schoolRepository.findById(id).orElse(null);
        if (school != null) {
            SchoolDTO schoolDTO = new SchoolDTO(school.getId(), school.getName(), school.getAddress());
            model.addAttribute("schoolDTO", schoolDTO);
        }
        return "school/edit-school";
    }

    @PostMapping("/edit")
    public String updateSchool(@ModelAttribute SchoolDTO schoolDTO) {
        School school = schoolRepository.findById(schoolDTO.getId()).orElse(null);
        if (school != null) {
            school.setName(schoolDTO.getName());
            school.setAddress(schoolDTO.getAddress());
            schoolRepository.save(school);
        }
        return "redirect:/school";
    }
}
