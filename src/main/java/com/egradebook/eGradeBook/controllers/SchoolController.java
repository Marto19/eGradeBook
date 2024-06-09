package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.DTOs.principle.PrincipalDTO;
import com.egradebook.eGradeBook.DTOs.school.SchoolDTO;
import com.egradebook.eGradeBook.entities.Principal;
import com.egradebook.eGradeBook.entities.School;
import com.egradebook.eGradeBook.exceptions.PrincipalNotFoundException;
import com.egradebook.eGradeBook.exceptions.SchoolNotFoundException;
import com.egradebook.eGradeBook.exceptions.TeacherNotFoundException;
import com.egradebook.eGradeBook.repositories.PrincipalRepository;
import com.egradebook.eGradeBook.repositories.SchoolRepository;
import com.egradebook.eGradeBook.services.PrincipalService;
import com.egradebook.eGradeBook.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/school")
public class SchoolController {

    private final SchoolService schoolService;
    private final PrincipalService principalService;

    public SchoolController(SchoolService schoolService, PrincipalService principalService) {
        this.schoolService = schoolService;
        this.principalService = principalService;
    }

    @GetMapping
    public String showSchool(Model model) {
        List<SchoolDTO> schoolDTOList = schoolService.getAllSchoolsDto();
        model.addAttribute("schoolDTOList", schoolDTOList);
        return "school/list-school";
    }

    @GetMapping("/create")
    public String showCreateSchoolForm(Model model) {
        List<PrincipalDTO> principalDTOList = principalService.getPrincipalDTOList();
        model.addAttribute("principalDTOList", principalDTOList);
        return "school/create-school";
    }

    @PostMapping("/create")
    public String createSchool(@RequestParam String schoolName,
                               @RequestParam String schoolAddress,
                               @RequestParam Long principalId) {

        try {
            schoolService.createSchool(schoolName, schoolAddress, principalId);
        } catch (PrincipalNotFoundException e) {
            // TODO Handle error properly
            throw new RuntimeException(e);
        }

        return "redirect:/school";
    }

    @GetMapping("/edit/{schoolId}")
    public String showEditSchoolForm(@PathVariable Long schoolId, Model model) {

        School school;
        List<PrincipalDTO> principalDTOList = principalService.getPrincipalDTOList();

        try {
            school = schoolService.findSchoolById(schoolId);
        } catch (SchoolNotFoundException e) {
            // TODO Handle properly
            throw new RuntimeException(e);
        }

        model.addAttribute("school", school);
        model.addAttribute("principalDTOList", principalDTOList);
        return "school/edit-school";
    }

    @PostMapping("/save")
    public String saveSchool(@RequestParam Long schoolId,
                             @RequestParam String schoolName,
                             @RequestParam String schoolAddress,
                             @RequestParam Long principalId) {

        try {
            schoolService.saveSchool(schoolId, schoolName, schoolAddress, principalId);
        } catch (PrincipalNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SchoolNotFoundException e) {
            // TODO Handle properly
            throw new RuntimeException(e);
        }

        return "redirect:/school";
    }

    @GetMapping("/delete/{schoolId}")
    public String deleteSchool(@PathVariable Long schoolId) {

        try {
            schoolService.deleteSchool(schoolId);
        } catch (SchoolNotFoundException e) {
            // TODO Handle Properly, Create Error Page
            throw new RuntimeException(e);
        }

        return "redirect:/school";
    }











    //    private final SchoolRepository schoolRepository;
//    private final PrincipalRepository principalRepository;
//
//    @Autowired
//    public SchoolController(SchoolRepository schoolRepository, PrincipalRepository principalRepository) {
//        this.schoolRepository = schoolRepository;
//        this.principalRepository = principalRepository;
//    }
//
//    @GetMapping
//    public String getAllSchools(Model model) {
//        List<SchoolDTO> schoolDTOList = schoolRepository.getAllSchoolsDto();
//        model.addAttribute("schoolDTOList", schoolDTOList);
//        return "school/list-school";
//    }
//
//    @GetMapping("/create")
//    public String createSchool(Model model) {
//        model.addAttribute("schoolDTO", new SchoolDTO());
//        return "school/create-school";
//    }
//
//    @PostMapping("/save")
//    public String saveSchool(@ModelAttribute SchoolDTO schoolDTO) {
//        School school = new School();
//        school.setName(schoolDTO.getName());
//        school.setAddress(schoolDTO.getAddress());
//        schoolRepository.save(school);
//        return "redirect:/school/get-schools";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteSchool(@PathVariable Long id) {
//        schoolRepository.deleteById(id);
//        return "redirect:/school/get-schools";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String showEditForm(@PathVariable Long id, Model model) {
//        School school = schoolRepository.findById(id).orElse(null);
//        if (school != null) {
//            SchoolDTO schoolDTO = new SchoolDTO(school.getId(), school.getName(), school.getAddress());
//            model.addAttribute("schoolDTO", schoolDTO);
//        }
//        return "school/edit-school";
//    }
//
//    @PostMapping("/edit")
//    public String updateSchool(@ModelAttribute SchoolDTO schoolDTO) {
//        School school = schoolRepository.findById(schoolDTO.getId()).orElse(null);
//        if (school != null) {
//            school.setName(schoolDTO.getName());
//            school.setAddress(schoolDTO.getAddress());
//            schoolRepository.save(school);
//        }
//        return "redirect:/school";
//    }
}
