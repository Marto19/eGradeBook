package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.exceptions.DayOfWeekNotFoundException;
import com.egradebook.eGradeBook.services.DayOfWeekService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor

@Controller
@RequestMapping("/day-of-week")
public class DayOfWeekController {

    private final DayOfWeekService dayOfWeekService;

    @GetMapping
    public String getAllDayOfWeek(Model model) {
        model.addAttribute("dayOfWeekList", dayOfWeekService.listdayOfWeek());
        return "/day-of-week/list-dayOfWeek";
    }

    @GetMapping("/create")
    public String showCreateForm() {
        return "/day-of-week/create-dayOfWeek";
    }

    @PostMapping("/create")
    public String createDayOfWeek(@RequestParam String dayOfWeekName) {
        dayOfWeekService.createDayOfWeek(dayOfWeekName);
        return "redirect:/day-of-week";
    }

    @GetMapping("/edit/{dayId}")
    public String showEditForm(@PathVariable Long dayId,
                               Model model) throws DayOfWeekNotFoundException {

        model.addAttribute("dayOfWeek",
                dayOfWeekService.findDayOfWeekById(dayId));
        return "day-of-week/edit-dayOfWeek";
    }

    @PostMapping("/update")
    public String updateDayOfWeek(@RequestParam Long dayId,
                                  @RequestParam String dayOfWeekName) throws DayOfWeekNotFoundException {
        dayOfWeekService.updateDayOfWeek(dayId, dayOfWeekName);
        return "redirect:/day-of-week";
    }

    @GetMapping("/delete/{dayId}")
    public String deleteDayOfWeek(@PathVariable Long dayId) throws DayOfWeekNotFoundException {
        dayOfWeekService.delteDayOfWeek(dayId);
        return "redirect:/day-of-week";
    }


}
