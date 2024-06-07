package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.DTOs.school.SchoolDTO;
import com.egradebook.eGradeBook.entities.Class;
import com.egradebook.eGradeBook.entities.ClassGradeNumberCatalog;
import com.egradebook.eGradeBook.entities.ClassLetterCatalog;
import com.egradebook.eGradeBook.entities.School;
import com.egradebook.eGradeBook.services.ClassGradeNumberCatalogService;
import com.egradebook.eGradeBook.services.ClassLetterCatalogService;
import com.egradebook.eGradeBook.services.ClassService;
import com.egradebook.eGradeBook.services.SchoolClassService;
import com.egradebook.eGradeBook.services.SchoolService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/school-classes")
public class SchoolClassesController {

    private final SchoolClassService schoolClassService;
    private final ClassLetterCatalogService classLetterCatalogService;
    private final ClassGradeNumberCatalogService classGradeNumberCatalogService;
    private final SchoolService schoolService;
    private final ClassService classService;

    public SchoolClassesController(SchoolClassService schoolClassesService, ClassLetterCatalogService classLetterCatalogService, ClassGradeNumberCatalogService classGradeNumberCatalogService, SchoolService schoolService, ClassService classService) {
        this.schoolClassService = schoolClassesService;
        this.classLetterCatalogService = classLetterCatalogService;
        this.classGradeNumberCatalogService = classGradeNumberCatalogService;
        this.schoolService = schoolService;
        this.classService = classService;
    }

    @GetMapping
    public String showSchoolClasses(Model model) {
        List<Class> classList = schoolClassService.getAllClasses();
        model.addAttribute("classList", classList);
        return "school-classes/list-class";
    }

    @GetMapping("/create")
    public String showCreateClassForm(Model model) {
        List<ClassLetterCatalog> classLetterCatalog = classLetterCatalogService.getAllClassGradeNumberCatalog();
        List<ClassGradeNumberCatalog> classGradeNumberCatalog = classGradeNumberCatalogService.getAllClassGradeNumberCatalog();
        List<SchoolDTO> schoolDTOList = schoolService.getAllSchools();

        model.addAttribute("classLetterCatalog", classLetterCatalog);
        model.addAttribute("classGradeNumberCatalog", classGradeNumberCatalog);
        model.addAttribute("schoolDTOList", schoolDTOList);

        return "school-classes/create-class";
    }

    @PostMapping("/create")
    public String createClass(@RequestParam Long schoolId,
                              @RequestParam Long gradeId,
                              @RequestParam Long letterId) {

            School school = schoolService.getSchoolById(schoolId);
            ClassGradeNumberCatalog classGradeNumber = classGradeNumberCatalogService.getClassGradeNumberById(gradeId);
            ClassLetterCatalog classLetter = classLetterCatalogService.getClassLetterById(letterId);

            Class newClass = new Class();
            newClass.setSchoolId(school);
            newClass.setClassGradeNumber(classGradeNumber);
            newClass.setClassLetterCatalog(classLetter);

            schoolClassService.saveClass(newClass);

        return "redirect:/school-classes";
    }

    @GetMapping("/edit/{classId}")
    public String showSchoolClassEditForm(@PathVariable Long classId, Model model) {
        Class editClass = classService.getClassById(classId);
        List<ClassGradeNumberCatalog> classGradeNumberCatalog = classGradeNumberCatalogService.getAllClassGradeNumberCatalog();
        List<ClassLetterCatalog> classLetterCatalog = classLetterCatalogService.getAllClassGradeNumberCatalog();

        model.addAttribute("editClass", editClass);
        model.addAttribute("classGradeNumberCatalog", classGradeNumberCatalog);
        model.addAttribute("classLetterCatalog", classLetterCatalog);

        return "school-classes/edit-class";
    }

    @PostMapping("/edit")
    public String updateSchoolClass(
            @RequestParam Long classId,
            @RequestParam Long gradeId,
            @RequestParam Long letterId) {

        ClassGradeNumberCatalog classGradeNumber = classGradeNumberCatalogService.getClassGradeNumberById(gradeId);
        ClassLetterCatalog classLetter = classLetterCatalogService.getClassLetterById(letterId);

        Class existingClass = schoolClassService.getClassById(classId);

        if (classGradeNumber != null
                && classLetter != null
                && existingClass != null) {

            existingClass.setClassGradeNumber(classGradeNumber);
            existingClass.setClassLetterCatalog(classLetter);

            schoolClassService.saveClass(existingClass);
        }

        return "redirect:/school-classes";
    }

    @GetMapping("/delete/{classId}")
    public String deleteSchoolClass(@PathVariable Long classId) {
        Class deleteClass = schoolClassService.getClassById(classId);

        if (deleteClass != null) { classService.delete(deleteClass); }

        return "redirect:/school-classes";
    }

}
