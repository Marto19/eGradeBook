package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.entities.Curriculum;
import com.egradebook.eGradeBook.exceptions.DayOfWeekNotFoundException;
import com.egradebook.eGradeBook.exceptions.SubjectNotFoundException;
import com.egradebook.eGradeBook.repositories.TeacherRepository;
import com.egradebook.eGradeBook.services.ClassService;
import com.egradebook.eGradeBook.services.CurriculumService;
import com.egradebook.eGradeBook.services.DayOfWeekService;
import com.egradebook.eGradeBook.services.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@AllArgsConstructor
@Controller
@RequestMapping("/curriculum")
public class CurriculumController {

    private final CurriculumService curriculumService;
    private final ClassService classService;
    private final DayOfWeekService dayOfWeekService;
    private final SubjectService subjectService;
    private final TeacherRepository teacherRepository;

    @GetMapping
    public String getAllCurriculum(Model model) {
        model.addAttribute("curriculumList", curriculumService.listCurriculum());
        return "curriculum/list-curriculum";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("classList", classService.getAllClasses());
        model.addAttribute("teacherList", teacherRepository.findAll());
        model.addAttribute("subjectList", subjectService.listAllSubjects());
        model.addAttribute("dayOfWeekList", dayOfWeekService.listdayOfWeek());

        return "curriculum/create-curriculum";
    }

    @PostMapping("/create")
    public String createCurriculum(@RequestParam Long classId,
                                   @RequestParam Long dayOfWeekId,
                                   @RequestParam Long subjectId,
                                   @RequestParam Long teacherId,
                                   @RequestParam LocalDate period)
            throws SubjectNotFoundException, DayOfWeekNotFoundException {

        curriculumService.createCurriculum(classId, dayOfWeekId, subjectId, teacherId, period);
        return "redirect:/curriculum";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Curriculum curriculum = curriculumService.findCurriculumById(id);

        model.addAttribute("curriculum", curriculum);
        model.addAttribute("classList", classService.getAllClasses());
        model.addAttribute("teacherList", teacherRepository.findAll());
        model.addAttribute("subjectList", subjectService.listAllSubjects());
        model.addAttribute("dayOfWeekList", dayOfWeekService.listdayOfWeek());

        return "curriculum/edit-curriculum";
    }

    @PostMapping("/update")
    public String editCurriculum(@RequestParam Long curriculumId,
                                 @RequestParam Long classId,
                                 @RequestParam Long dayOfWeekId,
                                 @RequestParam Long subjectId,
                                 @RequestParam Long teacherId,
                                 @RequestParam LocalDate period) throws SubjectNotFoundException, DayOfWeekNotFoundException {

        curriculumService.updateCurriculum(curriculumId, classId, dayOfWeekId, subjectId, teacherId, period);

        return "redirect:/curriculum";
    }

    @GetMapping("/delete/{curriculumId}")
    public String deleteCurriculum(@PathVariable Long curriculumId) {
        curriculumService.deleteCurriculum(curriculumId);
        return "redirect:/curriculum";
    }

}
