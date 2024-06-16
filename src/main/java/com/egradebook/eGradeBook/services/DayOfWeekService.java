package com.egradebook.eGradeBook.services;

import com.egradebook.eGradeBook.entities.DayOfWeekCatalog;
import com.egradebook.eGradeBook.exceptions.DayOfWeekNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DayOfWeekService {

    DayOfWeekCatalog findDayOfWeekById(Long id) throws DayOfWeekNotFoundException;
    DayOfWeekCatalog createDayOfWeek(DayOfWeekCatalog dayOfWeek);
    DayOfWeekCatalog updateDayOfWeek(DayOfWeekCatalog dayOfWeek);
    void delteDayOfWeek(Long id) throws DayOfWeekNotFoundException;
    List<DayOfWeekCatalog> listdayOfWeek();

    DayOfWeekCatalog createDayOfWeek(String dayOfWeek);
    DayOfWeekCatalog updateDayOfWeek(Long dayOfWeekId, String dayOfWeek) throws DayOfWeekNotFoundException;
}
